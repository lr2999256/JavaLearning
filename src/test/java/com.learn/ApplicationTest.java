package com.learn;

import com.learn.proxy.ITest;
import com.learn.proxy.ProxyFactory;
import com.learn.proxy.TestImpl;
import org.junit.Test;

/**
 * User : Rui
 * Date : 2017/9/19
 * Time : 17:07
 **/
public class ApplicationTest {

    @Test
    public void test1(){
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
}
