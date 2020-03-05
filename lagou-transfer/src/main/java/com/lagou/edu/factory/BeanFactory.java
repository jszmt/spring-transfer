package com.lagou.edu.factory;

/**
 * @author 应癫
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    private static MyApplicationContext applicationContext;


    static {
        try {
            applicationContext = new MyApplicationContext("com.lagou.edu");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        try {
            return applicationContext.getBean(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
