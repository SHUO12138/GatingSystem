package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.OpenRecord;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.DoorInfoServiceImpl;
import com.wechat.gatingsystem.service.Impl.OpenRecordServiceImpl;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by never on 2017/2/14.
 *
 */
@Controller
@RequestMapping(value = "/oDoor")
public class OpenDoorController {

    @Autowired
    private DoorInfoServiceImpl doorInfoServiceImpl;
    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;
    @Autowired
    private RelationServiceImpl relationServiceImpl;
    @Autowired
    private OpenRecordServiceImpl openRecordServiceImpl;

    @RequestMapping("/openDoor")
    public void openDoor(String dName, String uName,
                           HttpServletRequest request, HttpServletResponse response){
        String resp = null;
        String json = null;
        System.out.println(dName);
        DoorInfo dInfo = doorInfoServiceImpl.findByName(dName);
        UserInfo uInfo = userInfoServiceImpl.findByName(uName);
        if(dInfo != null && uInfo != null) {
            Relation relation = relationServiceImpl.findReByUDid(uInfo.userID, dInfo.doorID);
            Date date = new Date();
            OpenRecord record = new OpenRecord();
            record.setRelationID(relation.relationID);
            record.setRecordTime(date);
            if(relation != null){
                resp = "1";
                try {
                    openRecordServiceImpl.addOpenRecord(record);
                    serviceMain(resp);
                    System.out.println(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            resp = "0";
        }
        json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    public void serviceMain(String resp) throws Exception {

        /*ServerSocket server = null;     //定义ServerSocket类
        server = new ServerSocket(8888);//服务器在8888端口上进行监听
        Socket client = null;           //表示客户端
        boolean f = true;               //定义一个标记为
        while (f) {
            System.out.println("服务器运行，等待客户端连接");
            client = server.accept();//得到连接，程序进入阻塞状态
            ServerThread serverThread = new ServerThread(client, resp);
            Thread thread = new Thread(serverThread);
            thread.start();
            Thread.sleep(2000);

            //serverThread.stopThread();
//            if(serverThread.stopRequest){
//                break;
//            }
            //new Thread(new ServerThread(client, resp)).start();//每个客户代表一个线程
        }
        server.close();*/

        ServerSocket server = null; //定义ServerSocket类
        Socket client = null;       //表示客户端a

        server = new ServerSocket(8888);//服务器在8888端口上进行监听

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean f = true;//定义一个标记为
        while (f) {
            System.out.println("输入信息：");
            System.out.println("服务器运行，等待客户端连接");
            client = server.accept();//得到连接，程序进入阻塞状态

            ServerThread serverThread = new ServerThread(client, resp);
            Thread thread = new Thread(serverThread);
            thread.start();
            Thread.sleep(2000);

            //发送消息结束
            serverThread.stopThread();
            if(serverThread.stopRequest){
                break;
            }
            System.out.println("当前有新客户端连接");
        }
        server.close();
    }
}
