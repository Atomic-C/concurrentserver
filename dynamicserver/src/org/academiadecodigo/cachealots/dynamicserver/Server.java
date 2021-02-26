package org.academiadecodigo.cachealots.dynamicserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        int portNumber = 8080;

        try { //TODO: FOLLOW MODULE-2 NETWORK
            ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
