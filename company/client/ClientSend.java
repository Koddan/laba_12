package com.company.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSend extends Thread {

    Socket clientSocket;
    DataOutputStream outPut;

    public ClientSend(Socket clientSocket) {

        try {

            this.clientSocket = clientSocket;
            this.outPut = new DataOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        try {

            while (true) {

                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                outPut.writeUTF(message);

                if (message.equals("@quit")) {
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
