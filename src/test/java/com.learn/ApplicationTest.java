package com.learn;

import com.learn.lambda.Product;
import com.learn.proxy.ITest;
import com.learn.proxy.ProxyFactory;
import com.learn.proxy.TestImpl;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * User : Rui
 * Date : 2017/9/19
 * Time : 17:07
 **/
public class ApplicationTest {

    @Test
    public void test1(){

        System.out.println(System.getProperty("user.dir"));

        System.out.println(System.getProperty("line.separator"));

        Properties properties = System.getProperties();

        properties.entrySet().forEach((entry)-> {
            int a=0;
            System.out.println(a);
            System.out.println("k:" + entry.getKey() + "========v:" + entry.getValue());
        });

        // 目标对象
        ITest target = new TestImpl();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        ITest proxy = (ITest) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        String str = proxy.say();

        System.out.println("aaaaaaaaaaaaaaaaaa============"+str);
    }

    @Test
    public void test2() throws Exception{
        Class<?> clazz = Class.forName("com.learn.lambda.Product");
        // 在bean上进行内省
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

        PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();

        Product p = new Product();
        System.out.println("Product:");
        for (PropertyDescriptor pr : pro) {
            System.out.println(pr.getName() + " ");
        }
        System.out.println("");
        for (PropertyDescriptor pr : pro) {
            // 获取beal的set方法
            Method writeme = pr.getWriteMethod();
            if (pr.getName().equals("type")) {
                // 执行方法
                writeme.invoke(p, "xiong");
            }
            if (pr.getName().equals("id")) {
                writeme.invoke(p, 1);
            }
            if (pr.getName().equals("amt")) {
                writeme.invoke(p, BigDecimal.valueOf(23));
            }
            // 获取beal的get方法
            Method method = pr.getReadMethod();
            System.out.println(method.invoke(p) + " ");

        }

    }
}
