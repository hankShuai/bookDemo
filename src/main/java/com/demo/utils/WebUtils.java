package com.demo.utils;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

public class WebUtils {
   // static ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
    /**
     * 从ioc容器获取组件
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class <T> clazz){
        WebApplicationContext ioc = ContextLoader.getCurrentWebApplicationContext();
        return ioc.getBean(clazz);

    }
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 字符串转数字
     *
     * @param num 字符串
     * @return 0转失败
     */
    public static int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int parseInt(String num, int Default) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return Default;
    }
}
