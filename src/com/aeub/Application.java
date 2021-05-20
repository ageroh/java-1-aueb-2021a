package com.aeub;

import com.aeub.models.*;
import com.aeub.models.services.CardContractService;
import com.aeub.models.services.ContractService;
import com.aeub.models.services.TelecomService;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final int RENDER_THE_MENU = 1;

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
        Scanner in = new Scanner(System.in);
        for(;;){
            switch (menu(in)){
                case 2:
                    System.out.println("selected 2");
                    break;
                case 3:
                    System.out.println("selected 3");
                    break;
                case 0:
                    System.out.println("Application exited.");
                    return;
                case RENDER_THE_MENU:
                default:
                    renderTheMenu();
                    break;
            }
        }
    }

    private static void renderTheMenu() {
        System.out.println("1 - Renders this helping menu");
        System.out.println("2 - Create new Contract");
        System.out.println("3 - Show active contracts for a specific service");
        System.out.println("4 - Update statistics of a contract");
        System.out.println("5 - Calculate total ");


    }

    private static void initialize() {
        services.add(new ContractService());
        services.add(new ContractService());

        services.add(new CardContractService(20));
        services.add(new CardContractService(10));

        Contract contract = new Contract(
                services.get(0),
                "Jim",
                "Marley",
                "6954343534",
                LocalDate.now().plusDays(10),
                MethodOfPayment.cash);
        contracts.add(contract);


        Contract contract1 = new Contract(
                services.get(1),
                "John",
                "Doe",
                "6955432234",
                LocalDate.now().plusDays(1),
                MethodOfPayment.bankTransfer);
        contracts.add(contract1);

        Contract contract2 = new Contract(
                services.get(2),
                "Cloe",
                "Williams",
                "6955000031",
                LocalDate.now().plusDays(2),
                MethodOfPayment.cash);
        contracts.add(contract2);

        Contract contract3 = new Contract(
                services.get(3),
                "Petros",
                "Papadopoulos",
                "6955431114",
                LocalDate.now(),
                MethodOfPayment.creditCard);
        contracts.add(contract3);
    }

    public static int menu(Scanner in){
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        return RENDER_THE_MENU;
    }

}
