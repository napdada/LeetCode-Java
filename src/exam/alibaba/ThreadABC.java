package exam.alibaba;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阿里淘系技术部一面：三个线程 A、B、C，每个线程只能对应字符，按照"ABCABCABC输出"
 */
public class ThreadABC {
    private static int count = 0;
    final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadABC threadABC = new ThreadABC();
        threadABC.threadPrint();
    }

    public void threadPrint() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 30) {
                    lock.lock();
                    if (count % 3 == 0) {
                        System.out.print("A");
                        count++;
                    }
                    lock.unlock();
                }
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 30) {
                    lock.lock();
                    if (count % 3 == 1){
                        System.out.print("B");
                        count++;
                    }
                    lock.unlock();
                }
            }
        });

        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 30) {
                    lock.lock();
                    if (count % 3 == 2){
                        System.out.print("C");
                        count++;
                    }
                    lock.unlock();
                }
            }
        });

        A.start();
        B.start();
        C.start();
    }

}
