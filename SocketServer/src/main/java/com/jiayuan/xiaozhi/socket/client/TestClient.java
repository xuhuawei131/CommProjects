package com.jiayuan.xiaozhi.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by xiaoxian on 15/11/17.
 */
public class TestClient {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost", 9541);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String msg = reader.readLine();
            out.println(msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }

    public void domoreConn(int num){
        for(int i=1;i<=num;i++){

        }
    }
}
