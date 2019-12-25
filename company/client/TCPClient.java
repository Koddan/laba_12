package com.company.client;

import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    protected Socket socket;
    private String name;
    private ClientSend send;
    private ClientListen listen;

    public TCPClient(Socket clientSocket) {

        this.socket = clientSocket;
        this.send = new ClientSend(socket);
        this.listen = new ClientListen(socket);

    }

    public void connect() {

        try {

            DataOutputStream outPut = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream inPut = new DataInputStream(this.socket.getInputStream());
            System.out.println(inPut.readUTF());
            outPut.writeUTF((new Scanner(System.in)).nextLine());
            this.send.start();
            this.listen.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Socket getSocket() {
        return this.socket;
    }



}
