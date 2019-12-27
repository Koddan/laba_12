package com.company.server;

import com.company.client.TCPClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ClientThread extends Thread {

    private TCPClient client;
    private ArrayList<TCPClient> clients;

    public ClientThread(TCPClient client, ArrayList<TCPClient> clients) {
        this.client = client;
        this.clients = clients;
    }

    public void run() {

        while (true) {

            try {

                DataInputStream in = new DataInputStream(client.getSocket().getInputStream());
                String message = in.readUTF();

                if (message.contains("@senduser")) {
                    String name = message.split(" ")[1];
                    String out = "";
                    for (int i = 0; i < clients.size(); i++) {
                        if(clients.get(i).getName().equals(name)) {
                            for (int j = 2; j < message.split(" ").length; j++) {
                                out += message.split(" ")[j] + " ";
                            }
                            (new DataOutputStream(clients.get(i).getSocket().getOutputStream())).writeUTF(client.getName() + ": " + out);
                        }
                    }
                }
                else {
                    if (message.contains("@quit")) {
                        for (int i = 0; i < clients.size(); i++) {
                            if (clients.get(i).getSocket().equals(client.getSocket())) {
                                clients.remove(clients.get(i));
                            }
                        }
                        break;
                    }
                    else {

                        for (int i = 0; i < clients.size(); i++) {
                            if (!clients.get(i).getSocket().equals(client.getSocket())) {
                                (new DataOutputStream(clients.get(i).getSocket().getOutputStream())).writeUTF(client.getName() + ": " + message);
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
