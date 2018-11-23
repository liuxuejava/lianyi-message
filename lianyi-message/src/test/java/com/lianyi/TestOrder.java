package com.lianyi;

import java.util.UUID;

/**
 * Created by Stu on 2018/8/14.
 */
public class TestOrder {
    public static String getOrderIdByUUId(){
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodev = UUID.randomUUID().toString().hashCode();
        System.out.println(UUID.randomUUID().toString());
        if(hashCodev < 0){
            //有可能是负数
            hashCodev = -hashCodev;
        }
        //"%015d"的意思：0代表不足位数的补0，这样可以确保相同的位数，15是位数也就是要得到到的字符串长度是15，d代表数字。
        return machineId + String.format("%015d", hashCodev);
    }


    public static void main(String[] args) {
        /*String orderNo = getOrderIdByUUId();
        String orderNo2 = getOrderIdByUUId();
        String orderNo3 = getOrderIdByUUId();
        System.out.println(orderNo);
        System.out.println(orderNo2);
        System.out.println(orderNo3);*/
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString().hashCode());
        }
    }









}
