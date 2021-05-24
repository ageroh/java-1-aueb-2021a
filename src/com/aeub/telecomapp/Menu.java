package com.aeub.telecomapp;

import com.aeub.contracts.Statistic;

import java.util.Scanner;

import static com.aeub.telecomapp.Utils.tryLoop;

public class Menu {
    public static final int RENDER = 1;
    public static final int EXIT = 0;
    public static final int NEW_CONTRACT = 2;
    public static final int PRINT_ACTIVE = 3;
    public static final int UPDATE_STATISTICS = 4;
    public static final int CALCULATE_AND_PRINT_TOTAL_MONTHLY_COST = 5;
    public static final int CALCULATE_AND_PRINT_REMAINING_BALANCE = 6;
    public static final int CALCULATE_AND_PRINT_REMAINING_FREE_SPEECH_AND_FREE_SMS = 7;
    public static final int CALCULATE_AND_PRINT_REMAINING_DATA = 8;

    public void print() {
        System.out.println("Please select from the below choices.");
        System.out.println("1 - Prints this menu");
        System.out.println("2 - Create new Contract");
        System.out.println("3 - Show active contracts for a specific service");
        System.out.println("4 - Update statistics of a contract");
        System.out.println("5 - Calculate and print total monthly Cost");
        System.out.println("6 - Calculate and print total remaining Balance");
        System.out.println("7 - Calculate and print remaining free SMS/speech.");
        System.out.println("8 - Calculate and print total remaining Data");
        System.out.println("q - Exit application.");
    }

    public void printNewContractCode(int newContractCode) {
        System.out.println("New contract created with code: " + newContractCode);
        System.out.println();
    }

    public boolean confirmExit(Scanner in) {
        System.out.println("Are you sure(1. yes/2. no)? All the data will be lost!");
        String yesNo = in.next();
        if (yesNo.equals("1")) {
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

    public int tryGetPositiveInteger(Scanner in, String message, String errorMessage) {
        return tryLoop(() -> {
            System.out.print(message);
            String intStr = in.nextLine();
            int number = Integer.parseInt(intStr);
            if (number < 0) throw new Exception();
            return number;
        }, errorMessage);
    }

    public long tryGetPositiveLong(Scanner in, String message, String errorMessage) {
        return tryLoop(() -> {
            System.out.print(message);
            String longStr = in.nextLine();
            long number = Long.parseLong(longStr);
            if (number < 0) throw new Exception();
            return number;
        }, errorMessage);
    }

    public void welcome() {
        System.out.println("Welcome to Java Ufo Telecom Inc. ");
        System.out.println();
        System.out.println("Initializing services and contracts...");
        System.out.println();
    }

    public int selectContractType(Scanner in) {
        int contractType = tryLoop(() -> {
            System.out.println("Select a contract type (1,2,3):");
            System.out.println("  1 - Card Contract");
            System.out.println("  2 - Contract");
            System.out.println("  3 - Mobile Internet");
            String selection = in.nextLine();
            int selectionId = Integer.parseInt(selection);
            if (selectionId <= 0 || selectionId > 2) {
                throw new Exception();
            }
            return selectionId;
        }, "Must select a type from 1, 2 or 3.");
        return contractType;
    }

    public int trySelectContractCode(Scanner in) {
        int contractCode = tryLoop(() -> {
            System.out.print("Select the code of contract to update statistics:");
            String selection = in.nextLine();
            int code = Integer.parseInt(selection);
            if (mainApp.contracts.stream().anyMatch(z -> z.getCode() == code)) {
                return code;
            }
            throw new Exception();
        }, "Must select an existing contract.");
        return contractCode;
    }

    public Statistic gatherStatisticData(Scanner in) {
        Statistic statistic = new Statistic(
                tryGetPositiveInteger(in, "Enter Speech time to Mobile Networks in minutes:", "Please enter a positive number."),
                tryGetPositiveInteger(in, "Enter Speech time to Other Networks in minutes:", "Please enter a positive number."),
                tryGetPositiveInteger(in, "Enter of SMS sent:", "Please enter a positive number."),
                tryGetPositiveLong(in, "Enter megabytes of Data usage:", "Please enter a positive number."));
        return statistic;
    }
}
