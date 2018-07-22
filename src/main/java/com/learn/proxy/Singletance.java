package com.learn.proxy;

/**
 * @author : Rui
 * @Date : 2017/12/8
 * @Time : 9:47
 **/
public class Singletance {

    private static Singletance singletan;

    public static Singletance getSingletan() {
        if (singletan == null) {
            synchronized (singletan) {
                singletan = new Singletance();
            }
        }
        return singletan;
    }
}
