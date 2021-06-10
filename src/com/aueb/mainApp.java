package com.aeub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Team Number Id:
 * @author Marios ... ()
 * @author .....
 * */
public class mainApp {
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
                    case Menu.UPDATE_STATISTICS:
                        printActiveContracts();
                        Contract contract = getContract(menu, in);
                        Statistic statistic = menu.gatherStatisticData(in);
                        contract.addOrUpdateStaticForCurrentMonth(statistic);
                        System.out.println("Statistics updated for contract code: " + contract.getCode());
                        System.out.println();
                        menu.print();
                        break;
                    case Menu.CALCULATE_AND_PRINT_TOTAL_MONTHLY_COST:
                        contract = getContract(menu, in);
                        double cost = contract.calculateTotalMonthlyCost();
                        System.out.println("Monthly total cost for contract code:" + contract.getCode() + " is:" + cost);
                        break;
                    case Menu.CALCULATE_AND_PRINT_REMAINING_BALANCE:
                        contract = getContract(menu, in);
                        cost = contract.calculateRemainingBalance();
                        System.out.println("Monthly total cost for contract code:" + contract.getCode() + " is:" + cost);
                        break;
                    case Menu.CALCULATE_AND_PRINT_REMAINING_FREE_SPEECH_AND_FREE_SMS:
                        contract = getContract(menu, in);
                        int remainingFreeSpeechMinutes = contract.calculateRemainingFreeSpeech();
                        int remainingFreeSms = contract.calculateRemainingFreeSms();
                        System.out.println("Remaining Free minutes to speak:" + remainingFreeSpeechMinutes);
                        System.out.println("Remaining Free SMS:" + remainingFreeSms);
                        break;
                    case Menu.CALCULATE_AND_PRINT_REMAINING_DATA:
                        contract = getContract(menu, in);
                        int remainingFreeData = contract.calculateRemainingData();
                        System.out.println("Remaining Mobile Data:" + remainingFreeData);
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

    private static Contract getContract(Menu menu, Scanner in) {
        int code = menu.trySelectContractCode(in);
        Contract contract = contracts.stream().filter(z -> z.getCode() == code).findFirst().get();
        return contract;
    }

    // todo move this in a contracts class
    private static void printActiveContracts(int contractTypeId) {
        contracts.stream()
            .filter(z -> z.getTelecomService().getServiceId() == contractTypeId)
            .filter(z -> z.isActive())
            .forEach(c -> c.print());
    }

    // todo move this in a contracts class
    private static void printActiveContracts() {
        contracts.stream()
                .filter(z -> z.isActive())
                .forEach(c -> c.print());
    }

    // todo move this to a ContractFactory and inject ContractBuilder.
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

    // todo let those be provided by ContractFactory
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
                "6955000131",
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

    // todo let those be provided by TelecomServicesFactory
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