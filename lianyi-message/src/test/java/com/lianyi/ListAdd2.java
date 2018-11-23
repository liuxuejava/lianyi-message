package com.lianyi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu on 2018/8/24.
 */
public class ListAdd2 {
    private volatile static List list = new ArrayList();

    public void add(){
        list.add("winner");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        final ListAdd2  list2 = new ListAdd2();
        //1.实例化出来一个lock
        //当使用wait和notify的时候，一定要配合着synchronized关键字去使用
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //线程t1和t2一定要用同一把锁，就是都使用lock
                    synchronized (lock) {
                        for(int i=0;i<10;i++){
                            list2.add();
                            System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素..");
                            Thread.sleep(500);
                            if(list2.size() == 5){
                                System.out.println("已经发出通知");
                                lock.notify();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (lock) {
                    if(list2.size() != 5){
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程"+Thread.currentThread().getName()+"收到通知，线程停止..");
                    throw new RuntimeException();
                }

            }
        },"t2");

        t2.start();
        t1.start();

    }
}

