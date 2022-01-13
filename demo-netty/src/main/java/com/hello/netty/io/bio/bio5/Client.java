package com.hello.netty.io.bio.bio5;

import com.hello.netty.io.Config;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(Config.SERVER_IP, Config.SERVER_PORT);
        new Thread(() -> input(socket)).start();
        output(socket);
    }

    private static void output(Socket socket) {
        System.out.print("please input: ");
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void input(Socket socket){
        try {
            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg;
                if ((msg = bufferedReader.readLine()) != null) {
                    System.out.println("客户端接到数据：" + msg);
                    System.out.print("please input: ");
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
