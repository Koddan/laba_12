package com.company;

import com.company.client.TCPClient;
import java.io.IOException;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {

            TCPClient client = new TCPClient(new Socket("localhost", 5555));
            client.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
