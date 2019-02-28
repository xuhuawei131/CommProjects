package com.xuhuawei;

public class Main {

    public static void main(String[] args) {
        Queue queue = new Queue();
        ServerThread server = new ServerThread(queue);
        server.start();
        ClientThread[] clients = new ClientThread[5];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new ClientThread(queue, "" + i);
            clients[i].start();
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ie) {
        }
        server.shutdown();
    }
}
