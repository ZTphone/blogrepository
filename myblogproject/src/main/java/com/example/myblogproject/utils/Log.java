package com.example.myblogproject.utils;

public class Log {
    public static void log(String info,int value){
        System.out.println("##########");
        System.out.println(info+":"+value);
    }
    public static void log(String info,boolean value){
        System.out.println("##########");
        System.out.println(info+":"+value);
    }
    public static void log(String info,double value){
        System.out.println("##########");
        System.out.println(info+":"+value);
    }
    public static void log(String info,float value){
        System.out.println("##########");
        System.out.println(info+":"+value);
    }
    public static void log(String info,Object value){
        System.out.println("##########");
        System.out.println(info+":"+value);
    }
}
