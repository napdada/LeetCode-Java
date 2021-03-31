package practice.extend;

/**
 * Java 类中的各种成员初始化顺序测试
 * 1. 父类静态代码块、子类静态代码块；
 * 2. 父类普通代码块、子类普通代码块；
 * 3. 父类静态成员变量、子类静态成员变量；
 * 4. 父类普通成员变量、子类普通成员变量；
 * 5. 父类构造函数、子类构造函数
 */
public class FatherAndSon {
    public static void main(String[] args) {
        Son son = new Son();
    }
}

class Father {
    static {
        System.out.println("父类：静态代码块");
    }

    {
        System.out.println("父类：普通代码块");
    }

    private static int staticNum = initStaticNum();
    private int num = initNum();

    public Father() {
        System.out.println("父类：构造函数");
    }

    public static int initStaticNum() {
        System.out.println("父类：静态方法");
        return 0;
    }

    public int initNum() {
        System.out.println("父类：普通方法");
        return 0;
    }

}

class Son extends Father{
    static {
        System.out.println("子类：静态代码块");
    }

    {
        System.out.println("子类：普通代码块");
    }

    private static int staticNum = initStaticNum();
    private int num = initNum();

    public Son() {
        System.out.println("子类：构造函数");
    }

    public static int initStaticNum() {
        System.out.println("子类：静态方法");
        return 0;
    }

    public int initNum() {
        System.out.println("子类：普通方法");
        return 0;
    }
}