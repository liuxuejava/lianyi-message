package com.lianyi;

/**
 * Created by Stu on 2018/8/23.
 */

public class RunThread extends Thread{
    private int num = 0;

    public void setNum(int num){
        System.out.println(this.num);
        this.num = num;
    }
    public void run(){
        System.out.println(num);
    }
    public static void main(String[] args){

        RunThread t1 = new RunThread();
        t1.setNum(10);
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RunThread t2 = new RunThread();
        t2.setNum(20);
        t2.start();
    }


}

