package com.aeub.telecomapp;

import com.aeub.models.contracts.Contract;
import com.aeub.models.contracts.ContractBuilder;
import com.aeub.models.contracts.MethodOfPayment;
import com.aeub.models.services.CardContractService;
import com.aeub.models.services.ContractService;
import com.aeub.models.services.MobileInternetService;
import com.aeub.models.services.TelecomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static HashMap<String, TelecomService> availableServices = new HashMap<>();
    public static List<Contract> contracts = new ArrayList<>();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.welcome();
        initialize();
        menu.print();

        try (Scanner in = new Scanner(System.in)) {
            for (; ; ) {
                switch (menu.choose(in)) {
                    case Menu.NEW_CONTRACT:
                        // here we create a contract without asking for a code.
                        // its auto generated as the part A indicates.
                        int contractTypeId = menu.selectContractType(in);
                        int newContractCode = createNewContract(contractTypeId, in);
                        if (newContractCode > 0) {
                            menu.printNewContractCode(newContractCode);
                        }
                        break;
                    case Menu.PRINT_ACTIVE:
                        contractTypeId = menu.selectContractType(in);
                        printActiveContracts(contractTypeId);
                        break;
                    case Menu.EXIT:
                        if (menu.confirmExit(in)) return;
                        break;
                    case Menu.RENDER:
                    default:
                        menu.print();
                        break;
                }
            }
        }
    }

    private static void printActiveContracts(int contractTypeId) {
        contracts.stream()
            .filter(z -> z.getTelecomService().getServiceId() == contractTypeId)
            .filter(z -> z.isActive())
            .forEach(c -> c.print());
    }

    private static int createNewContract(int contractTypeId, Scanner in) {
        ContractBuilder cb = new ContractBuilder();

        cb.initContractService(contractTypeId)
            .addFirstName(in)
            .addLastName(in)
            .addMobilePhone(in)
            .addMethodOfPayment(in)
            .activationDate(in);

        Contract newContract = cb.build();
        contracts.add(newContract);

        return newContract.getCode();
    }

    private static void initialize() {
        initializeServices();
        initializeContracts();
    }

    private static void initializeContracts() {
        contracts.add(new Contract(
                availableServices.get("C100"),
                "Jim",
                "Marley",
                "6954343534",
                LocalDate.now().plusDays(10),
                MethodOfPayment.CASH));

        contracts.add(new Contract(
                availableServices.get("C40"),
                "John",
                "Doe",
                "6955432234",
                LocalDate.now().plusDays(1),
                MethodOfPayment.BANK_TRANSFER));

        contracts.add(new Contract(
                availableServices.get("CC20"),
                "Cloe",
                "Williams",
                "6955000031",
                LocalDate.now().plusDays(2),
                MethodOfPayment.CASH));

        contracts.add(new Contract(
                availableServices.get("MI100"),
                "Petros",
                "Papadopoulos",
                "6955431114",
                LocalDate.now(),
                MethodOfPayment.CREDIT_CARD));
    }

    private static void initializeServices() {
        availableServices.put("C100",
                new ContractService(
                        "Sumbolaio 100",
                        40,
                        0.25,
                        150,
                        100,
                        0.012,
                        0.16));

        availableServices.put("C40",
                new ContractService(
                        "Sumbolaio 40",
                        25,
                        0.0,
                        100,
                        40,
                        0.012,
                        0.16));

        availableServices.put("CC20",
                new CardContractService(
                        "Kartosumbolaio 20",
                        0,
                        20,
                        0.25,
                        100,
                        20,
                        0.012,
                        0.16));

        availableServices.put("CC50",
                new CardContractService(
                        "Kartosumbolaio 50",
                        0,
                        50,
                        0.25,
                        100,
                        50,
                        0.012,
                        0.16));

        availableServices.put("MI50",
                new MobileInternetService(
                        "Kinito Internet 50G",
                        20,
                        50,
                        0.15,
                        0.0));

        availableServices.put("MI100",
                new MobileInternetService(
                        "Kinito Internet 100G",
                        30,
                        100,
                        0.12,
                        0.1));
    }

}