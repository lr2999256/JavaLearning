package com.learn.proxy;

/**
 * User : Rui
 * Date : 2017/9/19
 * Time : 17:04
 **/
public class TestImpl implements ITest {
    @Override
    public String say() {
        System.out.println("say hello");
        return "hello";
    }
}
