package com.aeub;

import com.aeub.models.*;
import com.aeub.models.services.CardContractService;
import com.aeub.models.services.ContractService;
import com.aeub.models.services.TelecomService;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final int RENDER_THE_MENU = 1;
    private static final int EXIT_APPLICATION = 0;


    // κατάλογος των διαθέσιμων υπηρεσιών περιλαμβάνει αντικείμενα τύπου «υπηρεσία»
    public static List<TelecomService> services = new ArrayList<>();

    // κατάλογος των συμβολαίων περιλαμβάνει αντικείμενα τύπου «συμβόλαιο».
    public static List<Contract> contracts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Java Ufo Telecom Inc. ");
        System.out.println();
        System.out.println("Initializing services and contracts...");
        initialize();

        System.out.println("Please select from the below choices.");
        renderTheMenu();
        System.out.println();

        Scanner in = new Scanner(System.in);
        for(;;){
            switch (menu(in)){
                case 2:
                    int newContractCode = createNewContract(in);
                    if(newContractCode > 0) {
                        System.out.println("New contract created with code: " + newContractCode);
                    }
                    break;
                case 3:
                    System.out.println("selected 3");
                    break;
                case EXIT_APPLICATION:
                    System.out.println("Application exited.");
                    return;
                case RENDER_THE_MENU:
                    renderTheMenu();
                    break;
                default:
                    System.out.println("Please check your possible selections: \n");
                    renderTheMenu();
                    break;

            }
        }
    }

    private static int createNewContract(Scanner in) {
        System.out.println("Select a contract type:");
        System.out.println("  0 - ..");
        System.out.println("  1 - ....  ..");
        System.out.println("  2 - ......");
        System.out.println("  3 - .. ... ");
        int serviceId = in.nextInt();

        TelecomService service = services.get(serviceId);
        System.out.print("Enter client First name:");
        String firstName = in.next();

        System.out.print("Enter client Last name:");
        String lastName = in.next();

        System.out.print("Enter client Mobile Phone:");
        String mobilePhone = in.next();

        System.out.print("Enter service Activation Date(dd/MM/yyyy):");
        String date = in.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate activationDate = LocalDate.parse(date, formatter);

        System.out.println("Pick a method of payment:");
        System.out.println("0 - cash");
        System.out.println("1 - credit card");
        System.out.println("2 - bank transfer");
        MethodOfPayment methodOfPayment = MethodOfPayment.values()[in.nextInt()];

        Contract newContract = new Contract(
                service,
                firstName,
                lastName,
                mobilePhone,
                activationDate,
                methodOfPayment);
        contracts.add(newContract);

        return newContract.getCode();
    }

    private static void renderTheMenu() {
        System.out.println("1 - Renders this helping menu");
        System.out.println("2 - Create new Contract");
        System.out.println("3 - Show active contracts for a specific service");
        System.out.println("4 - Update statistics of a contract");
        System.out.println("5 - Calculate total monthly costs for all... ");
        System.out.println("6 - Calculate remaining free speech time...");
        System.out.println("q - Exit application.");

    }

    private static void initialize() {
        services.add(new ContractService());
        services.add(new ContractService());

        services.add(new CardContractService(20));
        services.add(new CardContractService(10));

        contracts.add(new Contract(
                services.get(0),
                "Jim",
                "Marley",
                "6954343534",
                LocalDate.now().plusDays(10),
                MethodOfPayment.CASH));

        contracts.add(new Contract(
                services.get(1),
                "John",
                "Doe",
                "6955432234",
                LocalDate.now().plusDays(1),
                MethodOfPayment.BANKTRANSFER));

        contracts.add(new Contract(
                services.get(2),
                "Cloe",
                "Williams",
                "6955000031",
                LocalDate.now().plusDays(2),
                MethodOfPayment.CASH));

        contracts.add(new Contract(
                services.get(3),
                "Petros",
                "Papadopoulos",
                "6955431114",
                LocalDate.now(),
                MethodOfPayment.CREDITCARD));
    }

    public static int menu(Scanner in){
        String s1 = in.next();
        if(s1.equals("exit") || s1 == "e" || s1 == "q") {
            return EXIT_APPLICATION;
        }
        try {
            return Integer.parseInt(s1);
        }
        catch (NumberFormatException e){
            System.out.println("Please check your possible selections: \n");
            return RENDER_THE_MENU;
        }
    }
}