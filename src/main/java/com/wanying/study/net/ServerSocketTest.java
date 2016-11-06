package com.wanying.study.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by wychenlong on 2016/10/19.
 */
public class ServerSocketTest {
    public static void main(String args[]){

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0",8888));
            ServerSocket serverSocke1t = new ServerSocket();
            serverSocke1t.bind(new InetSocketAddress("0.0.0.0",8888));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
