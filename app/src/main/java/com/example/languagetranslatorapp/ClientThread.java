package com.example.languagetranslatorapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;
    public static String recived_message;

    // Constructor
    public ClientThread(String sendMsg) {
        this.ipAddress = "192.168.100.21";
        this.port = 8001;
        this.sendMsg = sendMsg;
    }

    // Methods
    @Override
    public void run() {
        try {
            String ipAddress_ = "192.168.100.21";
            int port_ = 8001;
            Socket clientSocket = new Socket(ipAddress_, port_);

            // Sending message
            PrintWriter printWriter = Helper.getWriter(clientSocket);
            printWriter.println(sendMsg+"\r\n");

            // Reciving message
            BufferedReader bufferedReader = Helper.getReader(clientSocket);
            char[] rawPack = new char[1024];
            bufferedReader.read(rawPack);

            String pack = String.valueOf(rawPack);
            Log.d("RESPONSE: ", pack);
            String[] result = pack.split("#");
            System.out.println("Recived message is: " + result[0]);
            recived_message = result[0];
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}