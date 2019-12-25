package com.company.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ClientSend extends Thread {

    Socket clientSocket;
    DataInputStream inPut;

    public ClientSend(Socket clientSocket) {

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

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
