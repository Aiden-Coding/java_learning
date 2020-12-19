package com.test.sync;

public class MyThread extends Thread{
    @Override
    public void run() {

        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}