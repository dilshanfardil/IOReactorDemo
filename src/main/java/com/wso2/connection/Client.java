package com.wso2.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client
{
    private static final int SERVER_PORT = 7070;

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", SERVER_PORT);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes("Hello ..." + '\n');

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Response from Server : " + inFromServer.readLine());

        clientSocket.close();



    }
}
