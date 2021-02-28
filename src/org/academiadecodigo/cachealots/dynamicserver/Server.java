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

    public Server(){
        pool = Executors.newFixedThreadPool(21);
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


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome, you are player #" + connectionCount + " today.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            MyTasker myTask = new MyTasker(clientSocket, this); // We pass in it's clientSocket

            pool.submit(myTask);
        }

    }


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


        public MyTasker(Socket clientSocket, Server server) throws IOException {
            this.clientSocket = clientSocket; // This is the clientSocket the user connected with

            this.server = server;

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out = new PrintWriter(clientSocket.getOutputStream(), true);

        }
private  String line;

        @Override
        public void run() {

                    out.println("Welcome to the Kingdom of Erica");
                    try {
                        prompt = new Prompt(MyTasker.getIn(),new PrintStream(MyTasker.getOut()));
                        String[] options = {"I was", "I dont remember"};
                        MenuInputScanner scanner = new MenuInputScanner(options);
                        scanner.setMessage("Hey, you. You’re finally awake. You were trying to cross the border,right? ");
                        int answerIndex = prompt.getUserInput(scanner);
                        System.out.println(answerIndex);

                        if(answerIndex == 1){
                            String[] options2 = {"Magic cookies?", "sucks to be you"};
                            MenuInputScanner scanner2 = new MenuInputScanner(options2);
                            scanner2.setMessage("I was also trying to cross the border but I got busted for trying to get magic cookies across ");
                            int answerIndex2 = prompt.getUserInput(scanner2);
                        if(answerIndex2 == 1){
                            String[] options3 = {"Idk man, my memory is kinda fuzzy", "*punch him in the face"};
                            MenuInputScanner scanner3 = new MenuInputScanner(options3);
                            scanner3.setMessage("You dont know what Magic cookies are?Have you been living under a rock?");
                            int answerIndex3 = prompt.getUserInput(scanner3);
                            if(answerIndex3 == 1){
                                String[] options21 = {"The big D?", "Probably"};
                                MenuInputScanner scanner21 = new MenuInputScanner(options21);
                                scanner21.setMessage("Huumm,it seems you have caught the big D");
                                int answerIndex21 = prompt.getUserInput(scanner21);
                                if(answerIndex21 ==1){
                                    out.println("DIIIIS NUTS,HA GOTHIMMMMMM, *After that you lost all will to live and killed yourself,GAME OVER*");
                                }
                                if(answerIndex21 ==2){
                                    out.println("You got the Big D and died,GAME OVER");
                                }
                            }
                            if(answerIndex3 == 2){
                                String[] options20 = {"Try to punch him again", "Guess im gay now"};
                                MenuInputScanner scanner20 = new MenuInputScanner(options20);
                                scanner20.setMessage("*You try to punch him, but he swiftly dodges and kisses you on the lips*");
                                int answerIndex20 = prompt.getUserInput(scanner20);
                                if(answerIndex20 ==1){
                                    String[] options22 = {"Try to punch him yet again", "Guess im gay now"};
                                    MenuInputScanner scanner22 = new MenuInputScanner(options22);
                                    scanner22.setMessage("*You try to punch him, but he swiftly dodges and kisses you on the lips,again*");
                                    int answerIndex22 = prompt.getUserInput(scanner22);
                                    if(answerIndex22 == 1){
                                        String[] options23 = {"I mean yeah I guess im gay now", "Yeah I kinda asked for it"};
                                        MenuInputScanner scanner23 = new MenuInputScanner(options23);
                                        scanner23.setMessage("*You try to punch him, but he swiftly dodges and kisses you on the lips,again*");
                                        int answerIndex23 = prompt.getUserInput(scanner23);
                                        if(answerIndex23 == 1){
                                            out.println("GAME OVER#NOHOMO");
                                        }
                                        if(answerIndex23 == 2){
                                            out.println("Yeah you kinda did,GAME OVER");
                                        }
                                    }
                                    if(answerIndex22 == 2){
                                        out.println("GAME OVER");

                                    }
                                }
                                if(answerIndex20 == 2){
                                    out.println("GAME OVER");

                                }
                            }

                        }
                        if(answerIndex2 ==2){
                            String[] options4 = {"rip in pepperonis"};
                            MenuInputScanner scanner4 = new MenuInputScanner(options4);
                            scanner4.setMessage("Sucks to be me?!SUCKS TO BE YOU! *You get headbutted and die ");
                            int answerIndex4 = prompt.getUserInput(scanner4);
                            if(answerIndex4 == 1){
                                out.println("GAME OVER");
                            }
                        }
                        }





                        if(answerIndex ==2){
                                String[] options3 = {"Df is a magic cookie", "Yeah man I ate a whole bunch of them"};
                                MenuInputScanner scanner3 = new MenuInputScanner(options3);
                                scanner3.setMessage("You dont remember?Did you eat some magic cookies?");
                                int answerIndex3 = prompt.getUserInput(scanner3);

                                if(answerIndex3 == 1){
                                    String[] options30 = {"*Sadly racism is a thing in Erica Kingdom, and because of that, you died*"};
                                    MenuInputScanner scanner30 = new MenuInputScanner(options30);
                                    scanner30.setMessage("You mustn't be from around here if you dont know what magic cookie are, DIE IMMIGRANT ");
                                    int answerIndex30 = prompt.getUserInput(scanner30);
                                    if(answerIndex30 == 1){
                                        out.println("GAME OVER");
                                    }
                                }
                                if(answerIndex3 == 2){
                                    String[] options5 = {"I feel fIeneieeGDYGIagdgiyasgd", "Bagabi laca bachabe Lamac lamec bachalyas\n" +
                                            "Lamac cahi achababe Cabahagy sabalyos\n" +
                                            "Karrelyos Baryolos\n" +
                                            "Lagoz atha cabyolas\n" +
                                            "Samahac et famyolas\n" +
                                            "Harrahya"};
                                    MenuInputScanner scanner5 = new MenuInputScanner(options5);
                                    scanner5.setMessage("YOU ATE A WHOLE BUNCH OF THEM??! HOW THE HELL ARE YOU STILL ALIVE?");
                                    int answerIndex5 = prompt.getUserInput(scanner5);
                                    if(answerIndex5 ==1) {
                                        String[] options6 = {"Not the worst way to go tbh"};
                                        MenuInputScanner scanner6 = new MenuInputScanner(options6);
                                        scanner6.setMessage("You overdose on magic cookies and died");
                                        int answerIndex6 = prompt.getUserInput(scanner5);
                                        if(answerIndex6 == 1){
                                            out.println("GAME OVER");
                                        }

                                    }
                                    if(answerIndex5 == 2) {
                                        String[] options7 = {"I mean, I GUESS"};
                                        MenuInputScanner scanner7 = new MenuInputScanner(options7);
                                        scanner7.setMessage("*You woke up in your bed feeling well rested");
                                        int answerIndex6 = prompt.getUserInput(scanner7);
                                        if(answerIndex6 == 1){
                                            out.println("GEMU OVERURURUR");
                                        }
                                    }

                                }

                            }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                }


            }

