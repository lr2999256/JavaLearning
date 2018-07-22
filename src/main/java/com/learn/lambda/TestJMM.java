package com.learn.lambda;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Rui
 * @Date : 2018/7/22
 * @Time : 12:14
 **/
public class TestJMM {

//    public static volatile Integer count = 0;

    public static void main(String args[]){

        Runnable myRunable = JMM::getCount;

        for(int i=0;i<100;i++){
           Thread thread = new Thread(myRunable);
           thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class JMM{
        static int count = 0;

        static int getCount(){

            System.out.println("" + count++);
            return count;
        }
    }

    public static class JMM1{
        static AtomicInteger count = new AtomicInteger();

        static int getCount(){

            synchronized (count) {
                System.out.println("count=" + count.getAndIncrement());
                return count.get();
            }
        }
    }

    public static class JMM2{
        static Integer count = 0;

        synchronized static int getCount(){
            System.out.println("count=" + count++);
            return count;
        }

    }

    public static class JMM3{
        static volatile AtomicInteger count = new AtomicInteger(0);

        static int getCount(){
            System.out.println("count=" + count.getAndIncrement());
            return count.get();
        }

    }

    public static class JMM4{
        static int count = 0;

        static int getCount(){
            Lock lock = new ReentrantLock();
            lock.lock();
            System.out.println("count=" + count++);
            lock.unlock();
            return count;
        }

    }

}
