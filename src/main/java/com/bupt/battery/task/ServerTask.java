package com.bupt.battery.task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理Socket请求的线程类
 * 用于接受车辆的数据
 */
public class ServerTask implements Runnable {

    private ServerSocket server;
    Map<String, ClientHandler> clients = new ConcurrentHashMap<String, ClientHandler>();

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

            for (Socket socket = server.accept(); socket != null; socket = server.accept()) {
                System.out.println("客户端A建立连接："+ socket.getInetAddress()+socket.getPort());
                Runnable handler = new ClientHandler(socket, clients);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class ClientHandler implements Runnable {
        private final Socket socket;

        // 所有的写入都在os上执行
        private final PrintWriter os;
        private final BufferedReader is;

        // 用于记录该server中的clients
        private final Map<String, ClientHandler> clients;

        private String clientId;

        public ClientHandler(Socket socket, Map<String, ClientHandler> clients)
                throws IOException {
            this.socket = socket;
            this.clients = clients;
            this.os = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            this.is = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
        }

        @Override
        public void run() {
            try {
                // First line the client sends us is the client ID.
                clientId = is.readLine();
                clients.put(clientId, this);
                System.out.println(clients.toString());

                for (String line = is.readLine(); line != null; line = is.readLine()) {

                    int separatorIndex = line.indexOf(':');
                    if (separatorIndex <= 0) {
                        continue;
                    }
                    String toClient = line.substring(0, separatorIndex);
                    String message = line.substring(separatorIndex + 1);
                    ClientHandler client = clients.get(toClient);
                    if (client != null) {
                        client.sendMessage(clientId, message);
                        System.out.println("发送成功");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Client " + clientId + " terminated.");
                clients.remove(clientId);
                try {
                    socket.close();
                } catch (IOException ioe) {
                    // TODO Auto-generated catch block
                    ioe.printStackTrace();
                }
            }
        }

        public void sendMessage(String from, String message) {
            try {
                synchronized (os) {
                    os.println(message);
                    os.flush();
                }
            } catch (Exception e) {
                // We shutdown this client on any exception.
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ioe) {
                    // TODO Auto-generated catch block
                    ioe.printStackTrace();
                }
            }
        }


    }

}