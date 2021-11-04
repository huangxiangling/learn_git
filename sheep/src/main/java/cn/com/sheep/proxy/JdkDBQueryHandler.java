package cn.com.sheep.proxy;




import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理实现
 */
public class JdkDBQueryHandler implements InvocationHandler {

    private IDBQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(real == null){
            real = new DBQuery();
        }
        return real.req();
    }

    public static IDBQuery createJdkProxy(){
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDBQuery.class}, new JdkDBQueryHandler());
        return jdkProxy;
    }
}
