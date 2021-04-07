package com.yc.springframework.context;

import com.sun.istack.internal.NotNull;
import com.yc.springframework.beans.factory.anntaion.MyAutowired;
import com.yc.springframework.beans.factory.anntaion.MyQualifier;
import com.yc.springframework.beans.factory.anntaion.MyResource;
import com.yc.springframework.context.annotation.MyCompentScan;
import com.yc.springframework.context.annotation.MyConfiguration;
import com.yc.springframework.stereotype.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:40
 */
public class MyAnnotationConfigApplicationContext  implements  MyApplicationContext{


    private Map<String,Object> beanMap =new HashMap<>();
    private Map<String,Class> beanFactorMap =new HashMap<>();
    Set<Class> classes =new HashSet<>();

    @Override
    public Object getBean(String id) throws RuntimeException {
        Object o = beanMap.get(id);
//        if(o==null){
//            return new RuntimeException(id+"没有注册到容器中");
//        }
        return beanMap.get(id);

    }



    public MyAnnotationConfigApplicationContext(){

    }

    //构造方法
    public MyAnnotationConfigApplicationContext(Class<?>...compentClasses){
        register(compentClasses);
    }



    private void register(Class<?>[] compentClasses) {
        try {


            for(Class cls:compentClasses){
                //版本一： 值实现IOC ，MyPostConstruct，MyPreDestory
                //获得这个类的所有注解
                Annotation[] annotations = cls.getAnnotations();
                //遍历这个类的所有注解
                for(Annotation annotation:annotations){
                    //如果是Component注解则
                    if(annotation instanceof MyComponent ||annotation instanceof MyController ||annotation instanceof MyRepository || annotation instanceof MyService){
                        putBeanMapCls(cls);
                    }else if(annotation instanceof MyConfiguration){
                        MyCompentScan compentScan =(MyCompentScan) cls.getAnnotation(MyCompentScan.class);
                        String [] basePackages =null;
                        if(compentScan==null) {
                            basePackages = new String[] {new String(System.getProperty("user.dir")+cls.getPackage())};
                        }

                        basePackages =compentScan.basePackages();
                        scanPageagesClass(basePackages);
                    }
                    Object object = cls.newInstance();
                    handleMyBean(cls,object);
                }
                //扫描这个类的方法
                Object returnVal =null;
                String  name =null;
                for(Map.Entry<String,Object>map:beanMap.entrySet()){
                    Object object = map.getValue();
                    Class<?> objectClass = object.getClass();
                    Method[] declaredMethods = objectClass.getDeclaredMethods();
                    for(Method m:declaredMethods){
                            MyPostConstruct annotation = (MyPostConstruct)m.getAnnotation(MyPostConstruct.class);
                            if(annotation!=null){
                                //执行前置方法
                                m.invoke(object,m.getParameters());
                            }
                            //判断有无bean
                            MyBean bean =(MyBean)m.getAnnotation(MyBean.class);
                            if(bean!=null){
                                 returnVal = m.invoke(object, m.getParameters());
                                name=m.getName();
//                                beanMap.put(m.getName(),returnVal);
                            }
                    }
                }
                beanMap.put(name,returnVal);

            }
            //将所有的Bean类注册完到IOC容器之后，开始自动注入属性

           Dependeny_Injection();

        }catch (Exception e){
            e.printStackTrace();
            RuntimeException exception =new RuntimeException(e.getMessage());
            exception.printStackTrace();
        }
    }



    private void handleMyBean(Class cls,Object object) throws InvocationTargetException, IllegalAccessException {
        //1.获取cls中的所有的method
        Method[] methods = cls.getDeclaredMethods();
        //2.循环判断，每个method方法是有@MyBean注解
        for(Method m:methods){
            if(m.isAnnotationPresent(MyBean.class)){
                //3.有 invoke
                Object o = m.invoke(object, m.getParameters());
                beanMap.put(m.getName(),o);
            }
        }
    }

    //依赖注入
    private void Dependeny_Injection()   {
        try {
        for(Map.Entry<String,Object>map:beanMap.entrySet()){
            Object object = map.getValue();
            Class<?> objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for(Field field:fields){
                Annotation[] annotations = field.getAnnotations();
                for(Annotation an:annotations){
                    if(an instanceof MyResource){

                        String name =((MyResource) an).value();
                        name =(name.equals("")==true)?field.getName():name;
                        Object o = beanMap.get(name);
                        //获取到的对象的值
                        //注入字段值
                        field.set(object,o);
                    }

                    if(an instanceof MyAutowired){
                        Object o = beanMap.get(field.getName());
                        //MyAutowired就是按照类型注入
                        for(Map.Entry<String,Object> mm:beanMap.entrySet()){
                            Object TypeObject =beanMap.get(mm.getKey());
                            if(TypeObject==null||o==null) continue;

                            if(o.getClass()==TypeObject.getClass()){
                                field.set(object,o);
                            }
                        }

                    }
                }
            }
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void scanPageagesClass(String[] basePackages) throws Exception {
//        basePackages是所有的包名
        for(String p:basePackages){
            ClassLoader classLoader =Thread.currentThread().getContextClassLoader();
            String path =p.replace('.','/');
            //得到所有的里面的包
            Enumeration<URL> resources = classLoader.getResources(p);
            List<File> dirs =new ArrayList<>();

            //如果里面还有包就继续扫

            if(resources.hasMoreElements()==true){

                while(resources.hasMoreElements()){
                    URL resource = resources.nextElement();
                    dirs.add(new File(resource.getFile()));
                }


            }else{  //如果里面没有包

                URL resource = classLoader.getResource(path);
                dirs.add(new File(resource.getFile()));

            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, p));
            }
            for(Class cls:classes){
               if( cls.isAnnotationPresent(MyComponent.class)||cls.isAnnotationPresent(MyController.class)||cls.isAnnotationPresent(MyRepository.class)||cls.isAnnotationPresent(MyService.class)){
                   putBeanMapCls(cls);
                   String simpleName = cls.getSimpleName();
                   simpleName =simpleName.subSequence(0,1).toString().toLowerCase()+simpleName.substring(1,simpleName.length());
                   beanFactorMap.put(simpleName,cls);
               }
            }
        }
        System.err.println(beanFactorMap);
    }


    /**
     * 从物理路径反射具体的类集合
     *
     * @param directory   物理路径
     * @param packageName 包名
     * @return 类集合
     * @throws ClassNotFoundException 异常
     */
    private static List<Class> findClasses(@NotNull File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //递归查找文件夹【即对应的包】下面的所有文件
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + '.' + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }


    private void putBeanMapCls(Class cls) throws IllegalAccessException, InstantiationException {
        String simpleName = cls.getSimpleName();
        simpleName =simpleName.subSequence(0,1).toString().toLowerCase()+simpleName.substring(1,simpleName.length());
        //调用类的构造方法
        beanMap.put(simpleName,cls.newInstance());
        ClassLoader classLoader = this.getClass().getClassLoader();
    }

}
