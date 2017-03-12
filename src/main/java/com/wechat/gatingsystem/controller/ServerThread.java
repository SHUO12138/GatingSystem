package com.wechat.gatingsystem.controller;

import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by never on 2017/2/11.
 * 服务器端加入多线程
 */
public class ServerThread implements Runnable {

    private Socket client = null;//每一个Socket对象代表一个客户端
    private String inputStr = null;
    private Thread runThread;//得到当前线程
    public boolean stopRequest;//设置线程标志位

    public ServerThread(Socket client, String inputStr){
        this.client = client;
        this.inputStr = inputStr;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        stopRequest = false;
        PrintStream out = null;     //打印流输出最方便

        try {
            out = new PrintStream(client.getOutputStream());//那个类被outputStream实例化，就具有向这个类输出的功能
            boolean flg = true;
            while (flg) {
                if (inputStr == null || "".equals(inputStr)) {//表示没有内容
                    flg = false;//退出循环
                } else {
                    if (inputStr.equals("bye")) {
                        flg = false;
                    } else {
                        out.println("Echo:" + inputStr);
                    }
                }
            }
            client.close();
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }
    }

    //中断线程
    public void stopThread(){
        stopRequest = true;
        if(runThread != null){
            runThread.interrupt();
        }
    }
}
