package com.lianyi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu on 2018/8/24.
 */
public class ListAdd1 {
    private volatile static List list = new ArrayList();

    public void add(){
        list.add("winner");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        final ListAdd1  list1 = new ListAdd1();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for(int i=0;i<10;i++){
                        list1.add();
                        System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素..");
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    if(list1.size() == 5){
                        System.out.println("当前线程收到通知："+Thread.currentThread().getName()+"list size=5线程停止..");
                        throw new RuntimeException();
                    }
                }

            }
        },"t2");

        t1.start();
        t2.start();
    }
}