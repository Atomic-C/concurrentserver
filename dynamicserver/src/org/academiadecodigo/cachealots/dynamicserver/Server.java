package org.academiadecodigo.cachealots.dynamicserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        Server server = new Server(); // Start server instance
        server.startServer(); // Start server through startServer method





    }

    public void startServer() throws IOException {

        int connectionCount = 0;
        ServerSocket serverSocket = new ServerSocket(8080); // TCP SERVER SOCKET

        while (true) {

            Socket clientSocket = serverSocket.accept(); // Blocking method will wait - TCP CLIENT SOCKET
            connectionCount++; // Count established connections since server started
            System.out.println("Player #" + connectionCount + " has joined.");

             // TODO: IMPLEMENT MULTI THREAD
            // Out-puts message to client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome, you are player #" + connectionCount + " today.");
            //out.println("Welcome, you are player # " + connectionCount); // this only shows on nc, why?

            // We're not really using input from user...should i keep this here?
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }
    }

    // My tasker will be responsible for the client connections
    public class MyTasker implements  Runnable {

        public MyTasker(Socket clientSocket) {

        }

        @Override
        public void run() {

        }
    }

}
