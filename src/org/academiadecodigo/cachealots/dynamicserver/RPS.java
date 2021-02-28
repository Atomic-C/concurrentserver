package org.academiadecodigo.cachealots.dynamicserver;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class RPS {

    public void something() {
        // attach prompt to system's input/output
        Prompt prompt = new Prompt(System.in, System.out);

        // create a question, and set the message to be displayed
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("What is your name?");
    }

}
