public class Singleton{
    private Singleton(){}
    //饿汉式
    static private Singleton instance=new Singleton();//因为无法实例化，所以必须是静态的
    static public Singleton getInstance(){
        return instance;
    }
    //懒汉线程安全
    private static volatile Singleton instance2;
    public static Singleton getInstance2(){//双锁检查，线程安全
        if(instance2==null){
            synchronized (Singleton.class){
                if(instance2==null)
                    instance2=new Singleton();
            }
        }
        return instance2;
    }
}
