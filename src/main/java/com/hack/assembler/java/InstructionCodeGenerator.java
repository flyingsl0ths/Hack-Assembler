package com.hack.assembler.java;

public class InstructionCodeGenerator {
    public String dest(String destIn) {
        String destOut = "000";

        switch (destIn) {
            case "M":
                destOut = "001";
                break;
            case "D":
                destOut = "010";
                break;
            case "MD":
                destOut = "011";
                break;
            case "A":
                destOut = "100";
                break;
            case "AM":
                destOut = "101";
                break;
            case "AD":
                destOut = "110";
                break;
            case "AMD":
                destOut = "111";
                break;
        }
        return destOut;
    }

    public String comp(String compIn) {
        String compOut = "1111111";

        switch (compIn) {
            case "0":
                compOut = "0101010";
                break;
            case "1":
                compOut = "0111111";
                break;
            case "-1":
                compOut = "0111010";
                break;
            case "D":
                compOut = "0001100";
                break;
            case "A":
                compOut = "0110000";
                break;
            case "!D":
                compOut = "0001101";
                break;
            case "!A":
                compOut = "0110001";
                break;
            case "-D":
                compOut = "0001111";
                break;
            case "-A":
                compOut = "0110011";
                break;
            case "D+1":
                compOut = "0011111";
                break;
            case "A+1":
                compOut = "0110111";
                break;
            case "D-1":
                compOut = "0001110";
                break;
            case "A-1":
                compOut = "0110010";
                break;
            case "D+A":
                compOut = "0000010";
                break;
            case "D-A":
                compOut = "0010011";
                break;
            case "A-D":
                compOut = "0000111";
                break;
            case "D&A":
                compOut = "0000000";
                break;
            case "D|A":
                compOut = "0010101";
                break;
            case "M":
                compOut = "1110000";
                break;
            case "!M":
                compOut = "1110001";
                break;
            case "-M":
                compOut = "1110011";
                break;
            case "M+1":
                compOut = "1110111";
                break;
            case "M-1":
                compOut = "1110010";
                break;
            case "D+M":
                compOut = "1000010";
                break;
            case "D-M":
                compOut = "1010011";
                break;
            case "M-D":
                compOut = "1000111";
                break;
            case "D&M":
                compOut = "1000000";
                break;
            case "D|M":
                compOut = "1010101";
                break;
        }

        return compOut;
    }

    public String jump(String jumpIn) {
        String jumpOut = "000";
        switch (jumpIn) {
            case "JGT":
                jumpOut = "001";
                break;
            case "JEQ":
                jumpOut = "010";
                break;
            case "JGE":
                jumpOut = "011";
                break;
            case "JLT":
                jumpOut = "100";
                break;
            case "JNE":
                jumpOut = "101";
                break;
            case "JLE":
                jumpOut = "110";
                break;
            case "JMP":
                jumpOut = "111";
                break;
        }
        return jumpOut;
    }
}