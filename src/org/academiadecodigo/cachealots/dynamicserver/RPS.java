package org.academiadecodigo.cachealots.dynamicserver;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;

public class RPS {
    // Class properties
    //private Server server;
    private Server.MyTasker myTasker;
    private Socket socket;
    private BufferedReader in2;
    private PrintWriter out2;
    private InputStream in;
    private OutputStream out;
    private PrintStream printStream;
    private int gameHand;


    public RPS(InputStream in, OutputStream out) { // TODO: CONNECT Output and Input streams to prompt, so we can use netcat
        this.in = in;
        this.out = out;
    }

    public void startRPS() {

        // attach prompt to system's input/output
        Prompt prompt = new Prompt(in, printStream = new PrintStream(out));

        // create a question, and set the message to be displayed
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("What is your name?");

        // use the prompt to make the first question
        // this method will block the thread execution while waiting for user input
        String name = prompt.getUserInput(question1);


        // notice how the return type is defined by the scanner/question
        // create one more question
        StringInputScanner question2 = new StringInputScanner();
        question2.setMessage("What is your hand?");
        String handType = prompt.getUserInput(question2);

        printStream.println("Welcome " + name + "!");
        printStream.println("You have chosen " + handType);

        //System.out.println("User's name: " + name + " | User's age: " + age);

       gameHand = (int) Math.floor(Math.random() * 3); // between 0 and 2

        if (handType.equals("paper")) { //TODO: ASSIGN MATH RANDOM BETWEEN 0 TO 2 TO STRINGS ROCK PAPER SCISSORS

            //int gameHand = (int) Math.floor(Math.random() * 3); // between 0 and 2
            printStream.println(gameHand);
        }
        else if (handType.equals("rock")) {
            //int gameHand = (int) Math.floor(Math.random() * 3);
            printStream.println(gameHand);
        }
        else if (handType.equals("scissors")) {
            //int gameHand = (int) Math.floor(Math.random() * 3);
            printStream.println(gameHand);
        }
    }

}

/*
 TODO: KEEP IN MIND: --> Endless Possibilities

The extensive feature list follows:

Attach the prompt to any input/output stream
Fetch a number from the user, with optional validations
Fetch a string from the user, with optional validations
Create text menus easily from an array of options
Present messages on validation errors

 */
