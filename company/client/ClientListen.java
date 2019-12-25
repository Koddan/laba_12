package com.company.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientListen extends Thread {

    Socket clientSocket;
    DataOutputStream outPut;

    public ClientListen(Socket clientSocket) {

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

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
