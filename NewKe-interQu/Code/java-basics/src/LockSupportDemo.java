import java.util.concurrent.locks.LockSupport;

/**
 * 开启两个线程A、B，打印1到10，线程A打印奇数（1、3、5、7、9），线程B打印偶数（2、4、6、8、10）。
 */
public class LockSupportDemo {

    static Thread thread1;
    static Thread thread2;

    public static void main(String[] args) {
        thread1 = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                System.out.println(i);
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        });
        thread2 = new Thread(() -> {
            for (int i = 2; i <= 10; i = i + 2) {
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(thread1);
            }
        });
        thread1.start();
        thread2.start();
    }
}