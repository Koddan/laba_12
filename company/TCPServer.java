package com.company;

import com.company.client.TCPClient;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {

    private ServerSocket serverSocket;
    private ArrayList<TCPClient> clients;

    public TCPServer(int port) {

        try {
            this.clients = new ArrayList<TCPClient>();
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
                ClientThread clientThread = new ClientThread(newClient);
                clientThread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private class ClientThread extends Thread {

        private TCPClient client;

        public ClientThread(TCPClient client) {
            this.client = client;
        }

        public void run() {

            while (true) {

                try {

                    DataInputStream in = new DataInputStream(client.getSocket().getInputStream());
                    String message = in.readUTF();

                    for (int i = 0; i < clients.size(); i++) {
                        if(!clients.get(i).getSocket().equals(client.getSocket())) {
                            (new DataOutputStream(clients.get(i).getSocket().getOutputStream())).writeUTF(client.getName() + ": " + message);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
