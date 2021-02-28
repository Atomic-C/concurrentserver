package org.academiadecodigo.cachealots.dynamicserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// TODO: USE THESE CLIENTS TOGETHER TO TEST CONCURRENCY
public class Client2Test {
    public static void main(String[] args) {
        // STEP1: Get the host and the port from the command-line
        //String hostName = args[0];
        //int portNumber = Integer.parseInt(args[1]);

        // STEP2: Open a client socket, blocking while connecting to the server


        {
            try {
                Socket clientSocket = new Socket("127.0.0.1", 8080);
                // STEP3: Setup input and output streams
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println(in.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


// STEP4: Read from/write to the stream
// STEP5: Close the streams
// STEP6: Close the sockets
    }
}
