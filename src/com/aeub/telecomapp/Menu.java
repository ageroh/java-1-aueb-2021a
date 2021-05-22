package com.aeub.telecomapp;

import java.util.Scanner;

import static com.aeub.telecomapp.Utils.tryLoop;

public class Menu {
    public static final int RENDER = 1;
    public static final int EXIT = 0;
    public static final int NEW_CONTRACT = 2;
    public static final int PRINT_ACTIVE = 3;

    public void print() {
        System.out.println("Please select from the below choices.");
        System.out.println();
        System.out.println("1 - Prints this menu");
        System.out.println("2 - Create new Contract");
        System.out.println("3 - Show active contracts for a specific service");
        System.out.println("4 - Update statistics of a contract");
        System.out.println("5 - Calculate total monthly costs for all... ");
        System.out.println("6 - Calculate remaining free speech time...");
        System.out.println("q - Exit application.");
    }

    public void printNewContractCode(int newContractCode) {
        System.out.println("New contract created with code: " + newContractCode);
        System.out.println();
    }

    public boolean confirmExit(Scanner in) {
        System.out.println("Are you sure(yes/no)? All the data will be lost!");
        String yesNo = in.next();
        if (yesNo.equals("yes")) {
            System.out.println("Application exited.\n\n");
            System.out.println();
            return true;
        }
        return false;
    }

    public int choose(Scanner in) {
        String s1 = in.nextLine();
        if (s1.equals("exit") || s1.equals("e") || s1.equals("q")) {
            return EXIT;
        }
        try {
            return Integer.parseInt(s1);
        } catch (NumberFormatException e) {
            System.out.println("Please check your possible selections: \n");
            return RENDER;
        }
    }

    public void welcome() {
        System.out.println("Welcome to Java Ufo Telecom Inc. ");
        System.out.println();
        System.out.println("Initializing services and contracts...");
    }

    public int selectContractType(Scanner in) {
        int contractType = tryLoop(() -> {
            System.out.println("Select a contract type (0,1,2):");
            System.out.println("  0 - Card Contract");
            System.out.println("  1 - Contract");
            System.out.println("  2 - Mobile Internet");
            String selection = in.nextLine();
            int selectionId = Integer.parseInt(selection);
            if(selectionId < 0 || selectionId > 2) {
                throw new Exception();
            }
            return selectionId;
        }, "Must select a type from 0, 1 or 2.");
        return contractType;
    }
}
