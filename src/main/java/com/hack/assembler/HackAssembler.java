package com.hack.assembler;

import com.hack.assembler.java.InstructionCodeGenerator;
import com.hack.assembler.java.Parser;
import com.hack.assembler.java.SymbolTable;

import java.io.*;

public class HackAssembler {
    private final Parser parser;
    private final SymbolTable sTable = new SymbolTable();
    private final InstructionCodeGenerator codeGenerator = new InstructionCodeGenerator();
    private int VAR = 16;

    private final FileWriter writer;

    public HackAssembler(String sourceFile) throws IOException {
        parser = new Parser(sourceFile);
        writer = new FileWriter(sourceFile.split("\\.")[0] + ".asm");
    }

    public void writeASMFile() {
        try {
            this.writer.close();
        } catch (IOException e) {
            System.out.println("Compilation Error");
            System.exit(1);
        }
    }

    public void firstPass() throws IOException {
        int lineNumber = 1;
        while (this.parser.getNextLine() != null) {
            this.parser.advance();
            if (this.parser.getCommandType() == this.parser.getL_COMMAND()) {
                this.sTable.addEntry(this.parser.symbol(), lineNumber + 1);
            }
            lineNumber++;
        }
    }

    public void secondPass() throws IOException {
        this.parser.reloadFile();
        while (this.parser.getNextLine() != null) {
            this.parser.advance();
            String instruction;
            if (this.parser.getCommandType() == this.parser.getA_COMMAND()) {
                if (!this.sTable.contains(this.parser.symbol())) {
                    this.sTable.addEntry(this.parser.symbol(), this.VAR);
                    this.VAR++;
                }
                instruction = this.aToBInstruction(this.sTable.getAddress(this.parser.symbol())) + "\n";
                writer.write(instruction);
            } else if (this.parser.getCommandType() == this.parser.getC_COMMAND()) {
                instruction = this.cToBInstruction(this.parser.dest(), this.parser.comp(), this.parser.jump()) + "\n";

                writer.write(instruction);
            }
        }
    }

    public String aToBInstruction(int address) {
        String binaryInstruction = Integer.toBinaryString(address);
        int padding = 16 - binaryInstruction.length();
        return "0".repeat(Math.max(0, padding)) + binaryInstruction;
    }

    public String cToBInstruction(String dst, String cmp, String jmp) {
        return "111" + codeGenerator.dest(dst) + codeGenerator.comp(cmp) + codeGenerator.jump(jmp);

    }

    public static void main(String[] args) {
        try {
            if(args[0].endsWith("hack")) {
                HackAssembler hackAssembler = new HackAssembler(args[0]);
                hackAssembler.firstPass();
                hackAssembler.secondPass();
                hackAssembler.parser.closeFile();
                hackAssembler.writeASMFile();
            } else {
                System.out.println("Source file not valid");
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            System.exit(1);
        }
    }
}
