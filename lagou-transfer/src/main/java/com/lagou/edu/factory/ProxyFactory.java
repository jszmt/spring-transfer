package com.lagou.edu.factory;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 应癫
 *
 *
 * 代理对象工厂：生成代理对象的
 */
@Service
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*private ProxyFactory(){

    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }*/
    
    /**
     * @Description: 如果对象是实现接口的 则使用jdk代理否则使用cjlib
     * @param obj
     * @Date: 2020/3/5 17:50
     * @Author: wuguizhen
     * @Return java.lang.Object
     * @Throws
     */
    public Object  getProxy(Object obj){
        if(obj.getClass().getInterfaces() != null && obj.getClass().getInterfaces().length > 0){
            return getJdkProxy(obj);
        }
        return getCglibProxy(obj);
    }


    /**
     * Jdk动态代理
     * @param obj  委托对象
     * @return   代理对象
     */
    private Object getJdkProxy(Object obj) {

        // 获取代理对象
        return  Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        System.out.println("进行事物处理");
                        try{
                            // 开启事务(关闭事务的自动提交)
                            transactionManager.beginTransaction();

                            result = method.invoke(obj,args);

                            // 提交事务

                            transactionManager.commit();
                        }catch (Exception e) {
                            e.printStackTrace();
                            // 回滚事务
                            transactionManager.rollback();

                            // 抛出异常便于上层servlet捕获
                            throw e;

                        }

                        return result;
                    }
                });

    }


    /**
     * 使用cglib动态代理生成代理对象
     * @param obj 委托对象
     * @return
     */
    private Object getCglibProxy(Object obj) {
        return  Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try{
                    // 开启事务(关闭事务的自动提交)
                    transactionManager.beginTransaction();

                    result = method.invoke(obj,objects);

                    // 提交事务

                    transactionManager.commit();
                }catch (Exception e) {
                    e.printStackTrace();
                    // 回滚事务
                    transactionManager.rollback();

                    // 抛出异常便于上层servlet捕获
                    throw e;

                }
                return result;
            }
        });
    }
}
