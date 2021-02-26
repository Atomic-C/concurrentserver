package org.academiadecodigo.cachealots.dynamicserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {

        //int portNumber = 8080;
        // Need a server to listen to a client
        try { // TODO: FOLLOW MODULE-2 NETWORK - Sockets
            // java.net.ServerSocket implements TCP server sockets, used to wait for requests to come in over the network.
             ServerSocket serverSocket = new ServerSocket(8080); // TCP SERVER SOCKET

             // java.net.Socket implements TCP client sockets, capable of connecting to a specified server and port
             Socket clientSocket = serverSocket.accept(); // Blocking method will wait - TCP CLIENT SOCKET

             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

             out.println("Welcome to the server.");

             out.close(); // Should everything be closed?
             in.close();
             serverSocket.close();
             clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
