package com.hack.assembler.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private final int A_COMMAND = 0;
    private final int L_COMMAND = 1;
    private final int C_COMMAND = 2;

    String currentLine = "";

    private final String fileName;

    private BufferedReader sourceFile;

    public Parser(String fileName) throws IOException {
        this.fileName = fileName;
        sourceFile = new BufferedReader(new FileReader(this.fileName));
    }

    public void reloadFile() throws FileNotFoundException {
        this.sourceFile = new BufferedReader(new FileReader(this.fileName));
    }

    public int getA_COMMAND() {
        return this.A_COMMAND;
    }

    public int getL_COMMAND() {
        return this.L_COMMAND;
    }

    public int getC_COMMAND() {
        return this.C_COMMAND;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public void advance() throws IOException {
        while (getCurrentLine().isBlank() || getCurrentLine().startsWith("//")) {
            getNextLine();
        }
    }

    public String getNextLine() throws IOException {
        currentLine = sourceFile.readLine();
        return currentLine;
    }

    public void closeFile() throws IOException {
        this.sourceFile.close();
    }

    public int getCommandType() {
        char first_char = getCurrentLine().charAt(0);
        if (first_char == '@') {
            return A_COMMAND;
        } else if (first_char == '(') {
            return L_COMMAND;
        } else {
            return C_COMMAND;
        }
    }

    public String symbol() {
        if (getCommandType() == A_COMMAND) {
            return currentLine.substring(1).trim();
        } else if (getCommandType() == L_COMMAND) {
            return currentLine.substring(1, currentLine.length() - 1);
        }
        return " ";
    }

    public String dest() {
        if (getCommandType() == C_COMMAND && getCurrentLine().contains("=")) {
            return currentLine.split("=")[0];
        }
        return " ";
    }

    public String comp() {
        String comp = "";
        if (getCommandType() == C_COMMAND && getCurrentLine().contains("=")) {
            comp = currentLine.split("=")[1].replace(";", "");
        } else if (getCurrentLine().contains(";")) {
            comp = currentLine.split(";")[0];
        }
        return comp;
    }

    public String jump() {
        if (getCommandType() == C_COMMAND && getCurrentLine().contains("J")) {
            return currentLine.split(";")[1].replace(" ", "");
        }
        return " ";
    }
}