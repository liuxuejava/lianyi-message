package com.lianyi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Stu on 2018/8/24.
 */
public class ListAdd3 {
    private volatile static List list = new ArrayList();

    public void add(){
        list.add("winner");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        final ListAdd2  list2 = new ListAdd2();
        //这是并发包下的一个非常好用的工具类，实例化时的参数1代表需要调用几次
        //countDownLatch.countDown();才能叫醒，1就是调用1次即可，2就要调2次才行，
        //我们一般都用1就行了
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for(int i=0;i<10;i++){
                        list2.add();
                        System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素..");
                        Thread.sleep(500);
                        if(list2.size() == 5){
                            System.out.println("已经发出通知");
                            countDownLatch.countDown();
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
                if(list2.size() != 5){
                    try {
                        countDownLatch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程"+Thread.currentThread().getName()+"收到通知，线程停止..");
                throw new RuntimeException();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}

