package com.learn.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rui on 2017/6/26.
 */
public class BeanUtilsTest {
    public class Test{
        String a;
        String b;
        String c;

        public Test(){
            super();
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }

    public static void main(String[] args){
        BeanUtilsTest beanUtilsTest = new BeanUtilsTest();
        BeanUtilsTest.Test test =  beanUtilsTest.new Test();
        Test1 test1 =  new Test1();
        test.setA("a");
        test.setB("b");
        test.setC("c");
        Map<String,Object> map = BeanUtils.getProperties(test);
        String[] strs = new String[2];
        strs[0] = "a";
        strs[1] = "b";
//        BeanUtils.copyProperties(test,test1);
        BeanUtils.copyProperties(test,test1);
        Test1 test2 =  BeanUtils.toBean(Test1.class,test);
//        BeanUtils.copyProperties(test,test1, strs);
        System.out.print("a");

        List<String> list = getStringList("a","b");
        List<Integer> list1 = getStringList(1,1);
    }

    public static <T> List<T> getStringList(T a,T b){
        List<T> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}

