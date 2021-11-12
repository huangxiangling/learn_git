package cn.com.sheep.week02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8801);
        while (true){
            Socket socket = serverSocket.accept();
            service(socket);
        }
    }

    public static void service(Socket socket){

        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("HTTP/1.1 200 0k");
            writer.println("Content-Type:text/html;charset=utf-8");
            String body = "hello nio1";
            writer.println("Content-length" + body.getBytes().length);
            writer.println();
            writer.write(body);
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
