package cn.com.sheep.proxy;

public class ProxyTest {

    public static void main(String[] args) {
        IDBQuery jdkProxy = JdkDBQueryHandler.createJdkProxy();
        String req = jdkProxy.request();
        System.out.println("=======>"+req);

        String request = CglibDBQueryInterceptor.createCglibProxy().request();
        System.out.println("------->"+request);
    }
}
