package com.lianyi;

/**
 * Created by Stu on 2018/8/21.
 */

public class DirtyRead {
    private String username = "bjfdz";
    private String password = "123";

    public synchronized void setValue(String username, String password) {
        //先修改username的值
        this.username = username;
        //在修改密码之前先让程序休息两秒
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //修改密码
        this.password = password;
        //修改完后打印用户名和密码信息
        System.out.println("setValue最终结果：username=" + username + ",password=" + password + ",当前线程的ID" + Thread.currentThread().getName());
    }

    public synchronized void getValue() {
        System.out.println("getValue方法得到：username = " + this.username + ", password = " + this.password + ",当前线程的ID" + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                dr.setValue("zhangsan", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.getValue();
    }
}

