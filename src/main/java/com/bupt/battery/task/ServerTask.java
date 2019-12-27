package com.bupt.battery.task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理Socket请求的线程类
 * 用于接受车辆的数据
 */
public class ServerTask implements Runnable {

    private ServerSocket server;

    /**
     * 构造函数
     */
    public ServerTask(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            System.out.println("等待与客户端建立连接...");
            while(true)
            {
                Socket socket = server.accept();
                System.out.println("客户端已连接："+socket.getInetAddress());
                new HandlerThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 多线程跟客户端Socket进行通信
     */
    private static class HandlerThread implements Runnable {
        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        public void run() {
            try {
                // 跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了

                String message[] = {
                    "(390287, 10, datetime.datetime(2018, 1, 8, 6, 48), 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.61, 3.59, 3.61, 3.6, 3.56, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.61, 3.6, 3.6, 3.6, 3.6, 3.61, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None)",
                            "(390288, 10, datetime.datetime(2018, 1, 8, 6, 48), 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.61, 3.59, 3.6, 3.6, 3.56, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.61, 3.6, 3.6, 3.6, 3.6, 3.61, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None)",
                            "(390289, 10, datetime.datetime(2018, 1, 8, 6, 48), 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.61, 3.59, 3.6, 3.6, 3.56, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None)",
                            "(390290, 10, datetime.datetime(2018, 1, 8, 6, 48), 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.59, 3.6, 3.6, 3.56, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None)",
                            "(390291, 10, datetime.datetime(2018, 1, 8, 6, 49), 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.61, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.59, 3.6, 3.6, 3.56, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, 3.6, 3.6, 3.6, 3.59, 3.6, 3.59, 3.59, 3.59, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.59, 3.59, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None)"
                };


                for (int i = 0; i<5; ++i) {

                    socket.getOutputStream().write(message[i].getBytes("UTF-8"));
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String temp;
                int index;
                while ((temp = br.readLine()) != null) {
                    if ((index = temp.indexOf("eof")) != -1) { // 遇到eof时就结束接收
                        sb.append(temp.substring(0, index));
                        break;
                    }
                    sb.append(temp);
                }
                System.out.println("Form Cliect[port:" + socket.getPort()
                        + "] 消息内容:" + sb.toString());


                br.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }

}