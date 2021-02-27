package org.academiadecodigo.cachealots.dynamicserver;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    ExecutorService pool;

    public Server(){
        pool = Executors.newFixedThreadPool(4);
    }

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
            System.out.println("Player #" + connectionCount + " has joined at socket: " + clientSocket);

            // Out-puts message to client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome, you are player #" + connectionCount + " today.");
            //out.println("Welcome, you are player # " + connectionCount); // this only shows on nc, why?

            // We're not really using input from user...should i keep this here?
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // TODO: IMPLEMENT MULTI THREAD
            // Here we create a new task
            MyTasker myTask = new MyTasker(clientSocket); // We pass in it's clientSocket

            //Thread thread = new Thread(myTask);
            //thread.start();
            pool.submit(myTask);
        }
    }

    // My tasker will be responsible for the client connections
    public class MyTasker implements  Runnable {
        // Class properties used to save what we pass in to the constructor
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;


        // Class constructor
        public MyTasker(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket; // This is the clientSocket the user connected with

            // TODO: Ask mcs about the difference between the two "out"s
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true); // What is the difference between these two??
            out = new PrintWriter(clientSocket.getOutputStream(), true);

        }

        @Override
        public void run() {

            // access directly Thread class and print it's name
            System.out.println("This " + Thread.currentThread().getName() + " has started.");

            while (!clientSocket.isClosed()) { // While client socket isn't closed

                try {

                    String line = in.readLine(); // Blocking readLine waiting for new messages

                if (line.equals("quit")) { // if user inserts "quit" string
                    this.clientSocket.close(); // this. to be sure the current clientSocket is the one closing
                    System.out.println(Thread.currentThread().getName() + " quit, closing socket.");
                }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
    }

}
