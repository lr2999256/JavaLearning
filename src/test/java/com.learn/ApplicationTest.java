package com.learn;

import com.learn.lambda.Product;
import com.learn.proxy.ITest;
import com.learn.proxy.ProxyFactory;
import com.learn.proxy.Singletance;
import com.learn.proxy.TestImpl;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Properties;

/**
 * User : Rui
 * Date : 2017/9/19
 * Time : 17:07
 **/
public class ApplicationTest {

    @Test
    public void test1(){

        String mblNo =  "+86-787058105";
        String tmp[] = mblNo.split("-");
        String mblNoLast = "";
        if(tmp.length > 1 ){
            mblNoLast = tmp[1];
        }else{
        }

        Base64.getEncoder().encode("abc".getBytes());

        //如果是1打头，发给cps时加上前缀86,如果是其他6/7打头，加上255
        if(mblNoLast.startsWith("1")){
            mblNo = "86"+mblNoLast;
        }else if(mblNoLast.startsWith("6")||mblNoLast.startsWith("7")){
            mblNo ="255"+mblNoLast;
        }else {
            mblNo ="";
        }


        String shortCode = "";
        String mercId = "8888880000018505";
        if(mercId.length()>6){
            shortCode = "6"+mercId.substring(mercId.length()-6,mercId.length());
        }else{
            shortCode = "6"+mercId;
        }

//        mblNo = "86"+adc;
        String addr = "jinBian_";
        String abc[] = addr.split("_",-1);

        String str1 = "a  b     c  d e f      g";

        String [] arr = str1.split("\\s+");
        for(String ss : arr){
            System.out.println(ss);
        }


//        Constructor constructor = new Constructor(TestImpl.class);
//        try {
//            constructor.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        BigDecimal a = BigDecimal.valueOf(50);
        if(a.compareTo(BigDecimal.valueOf(5))==-1){
            System.out.print("abc");
        }else if(a.compareTo(BigDecimal.valueOf(10))>=0&&a.compareTo(BigDecimal.valueOf(20))<=0){
            System.out.print("abc");
        }else {
            System.out.print("abc");
        }

        Singletance singletance = Singletance.getSingletan();


        try {
            BigDecimal balAmt = new BigDecimal("abc");
        }catch (Exception e){

        }
        System.out.println(System.getProperty("user.dir"));

        System.out.println(System.getProperty("line.separator"));

        Properties properties = System.getProperties();

        properties.entrySet().forEach((entry)-> {
            int a1=0;
            System.out.println(a1);
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
