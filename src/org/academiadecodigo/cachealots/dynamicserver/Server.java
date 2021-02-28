package org.academiadecodigo.cachealots.dynamicserver;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    ExecutorService pool;
    Prompt prompt;
    ArrayList<MyTasker> cona;

    public Server(){
        pool = Executors.newFixedThreadPool(4);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(); // Start server instance



        server.startServer(); // Start server through startServer method









    }

    public void startServer() throws IOException {
        cona = new ArrayList<>();
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
            MyTasker myTask = new MyTasker(clientSocket, this); // We pass in it's clientSocket
            cona.add(myTask);


            //Thread thread = new Thread(myTask);
            //thread.start();
            pool.submit(myTask);
        }
    }

    // My tasker will be responsible for the client connections
    public static class MyTasker implements  Runnable {
        // Class properties used to save what we pass in to the constructor
        private static Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private Prompt prompt;
        private  Server server;


        public static InputStream getIn() throws IOException {
            return clientSocket.getInputStream();
        }

        public static OutputStream getOut() throws IOException {
            return clientSocket.getOutputStream();
        }

        // Class constructor
        public MyTasker(Socket clientSocket, Server server) throws IOException {
            this.clientSocket = clientSocket; // This is the clientSocket the user connected with
            this.server = server;

            // TODO: Ask mcs about the difference between the two "out"s
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true); // What is the difference between these two??
            out = new PrintWriter(clientSocket.getOutputStream(), true);

        }
private  String line;

        @Override
        public void run() {

            // access directly Thread class and print it's name
            System.out.println("This " + Thread.currentThread().getName() + " has started.");

            while (!clientSocket.isClosed()) { // While client socket isn't closed

                try {

                     line = in.readLine(); // Blocking readLine waiting for new messages
                    System.out.println(line);

                if (line.equals("quit")) { // if user inserts "quit" string
                    this.clientSocket.close(); // this. to be sure the current clientSocket is the one closing
                    System.out.println(Thread.currentThread().getName() + " quit, closing socket.");
                }

                else if (line.equals("RPS")) {
                    out.println("Welcome to Rock Paper Scissors!");
                    try {
                        prompt = new Prompt(MyTasker.getIn(),new PrintStream(MyTasker.getOut()));
                        StringInputScanner question1 = new StringInputScanner();
                        question1.setMessage("What is your name?");
                        String name = prompt.getUserInput(question1);
                        System.out.println(name);
                        StringInputScanner question2 = new StringInputScanner();
                        question2.setMessage("Hello " + name);
                        System.out.println("pick motherfucker");
                        String[] options = {"Rock", "Paper","Isqueiro"};
                        MenuInputScanner scanner = new MenuInputScanner(options);
                        scanner.setMessage("PICK BITCH");
                        int answerIndex = prompt.getUserInput(scanner);

                        System.out.println("User wants to " + options[answerIndex - 1]);





                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }

        public  String getLine() {
            return line;
        }
    }

}
