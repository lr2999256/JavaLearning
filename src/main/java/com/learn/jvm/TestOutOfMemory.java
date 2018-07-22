package com.learn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rui
 * @Date : 2018/7/14
 * @Time : 9:31
 **/
public class TestOutOfMemory {

    static class OOMObject{

    }

    public static void main(String args[]){
        /*

        -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError

        java.lang.OutOfMemoryError: Java heap space

        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
        */

        List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
