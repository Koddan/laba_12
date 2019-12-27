package com.company.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ClientListen extends Thread {

    Socket clientSocket;
    DataInputStream inPut;

    public ClientListen(Socket clientSocket) {

        try {

            this.clientSocket = clientSocket;
            this.inPut = new DataInputStream(clientSocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        try {

            while (true) {

                String message = inPut.readUTF();
                System.out.println(message);

                if (message.contains("Disconnected")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
