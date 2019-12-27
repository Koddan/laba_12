package com.company.server;

import com.company.client.TCPClient;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {

    private ServerSocket serverSocket;
    private ArrayList<TCPClient> clients;

    public TCPServer(int port) {

        try {
            this.clients = new ArrayList<>();
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {

        while (true) {

            try {

                Socket clientSocket = serverSocket.accept();
                DataOutputStream outPut = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream inPut = new DataInputStream(clientSocket.getInputStream());
                outPut.writeUTF("Enter your name: ");
                String clientName = inPut.readUTF();
                TCPClient newClient = new TCPClient(clientSocket);
                newClient.setName(clientName);
                this.clients.add(newClient);
                ClientThread clientThread = new ClientThread(newClient, this.clients);
                clientThread.start();
                outPut.writeUTF("Connected");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
