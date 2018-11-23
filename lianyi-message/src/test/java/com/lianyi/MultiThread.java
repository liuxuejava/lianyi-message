package com.lianyi;

/**
 * Created by Stu on 2018/8/20.
 */

public class MultiThread {
    //定义一个静态变量num，并附初始值为0
    private int num = 0;
    public synchronized void printNum(String tag){
        try {
            if(tag.equals("a")){
                num = 100;
                System.out.println("tag a, set num over!");//打印给num赋值完毕
                Thread.sleep(1000);//休息1秒，之所以这样是为了让大家看到两个线程互不干扰，如果不休息的话，瞬间执行完了，看不出效果
            }else {
                num = 200;
                System.out.println("tag b, set num over!");
            }
            System.out.println("tag " + tag + ", num = "+num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注意观察run方法输出顺序
    public static void main(String[] args){
        //两个不同的对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(() -> {
            m1.printNum("a");
        });

        Thread t2 = new Thread(() -> {
            m2.printNum("b");
        });

        t1.start();
        t2.start();
    }
}

