package org.academiadecodigo.cachealots.dynamicserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.startServer();





    }

    public void startServer() throws IOException {

        int connectionCount = 0;
        ServerSocket serverSocket = new ServerSocket(8080); // TCP SERVER SOCKET

             // TODO: IMPLEMENT A WHILE LOOP WITH DECRECREMENTOR
        while (true) {

            Socket clientSocket = serverSocket.accept(); // Blocking method will wait - TCP CLIENT SOCKET

            connectionCount++;

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome, you are player # " + connectionCount);
            //out.println("Welcome, you are player # " + connectionCount); // this only shows on nc

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }
    }


}
