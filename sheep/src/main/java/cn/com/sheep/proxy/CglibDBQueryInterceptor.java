package cn.com.sheep.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理实现
 */
public class CglibDBQueryInterceptor implements MethodInterceptor {

    private IDBQuery real = null;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(real == null){
            real = new DBQuery();
        }
        return real.request();
    }

    public static IDBQuery createCglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDBQueryInterceptor());
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }
}
