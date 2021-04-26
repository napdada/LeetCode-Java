package exam.alibaba;

import java.util.HashMap;

/**
 * 阿里支付宝一面：线程安全问题
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private int C;
    // 方法一：使用 HashMap 存储线程 ID 和 C 值
    private final HashMap<String, Integer> map = new HashMap<>();

    private Singleton() {}

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public synchronized void A(String threadName) {
        C = (int) (Math.random() * 10);
        map.put(threadName, C);
        System.out.println(threadName + "： A "  + C);
    }

    public synchronized void B(String threadName) {
        System.out.println(threadName + "： B "  + map.get(threadName));
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread("" + i){
                public void run(){
                    Singleton singleton = Singleton.getUniqueInstance();
                    System.out.println("Thread: " + getName() + " running");
                    singleton.A(getName());
                    singleton.B(getName());

                    // 方法二：若不使用 HashMap，可以在调用 A、B 方法时，将两个方法加锁捆绑执行如下
//                    synchronized (Singleton.class) {
//                        singleton.A(getName());
//                        singleton.B(getName());
//                    }
                }
            }.start();
        }
    }
}
