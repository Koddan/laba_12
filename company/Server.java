package com.company;

import com.company.server.TCPServer;

public class Server {

    public static void main(String[] args) {

        TCPServer server = new TCPServer(5555);
        server.start();

    }

}
