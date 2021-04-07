import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 19:51
 */
public class Test {
    static final Object obj = new Object();

    public static void m1() {
        synchronized (obj) {
            // 同步块A
            m2();
        }
    }

    public static void m2() {
        synchronized (obj) {
            // 同步块B
            m3();
        }
    }

    public static void m3() {
        synchronized (obj) {
            // 同步块C
        }
    }
}
