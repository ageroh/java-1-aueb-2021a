package com.aeub;

import com.aeub.models.*;
import com.aeub.models.services.CardContractService;
import com.aeub.models.services.ContractService;
import com.aeub.models.services.TelecomService;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // κατάλογος των διαθέσιμων υπηρεσιών περιλαμβάνει αντικείμενα τύπου «υπηρεσία»
    public static List<TelecomService> services = new ArrayList<>();

    // κατάλογος των συμβολαίων περιλαμβάνει αντικείμενα τύπου «συμβόλαιο».
    public static List<Contract> contracts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welome ");
        System.out.println("Initializing services and contracts...");
        initialize();

        System.out.println("Please select from the below choices.");




    }

    private static void initialize() {
        services.add(new ContractService());
        services.add(new ContractService());

        services.add(new CardContractService(20));
        services.add(new CardContractService(10));

        Contract contract = new Contract(services.get(0),
                "Jim",
                "Marley",
                "6954343534",
                LocalDate.now().plusDays(10),
                "creditcard");

        Contract contract1 = new Contract(services.get(1),
                "John",
                "Doe",
                "6955432234",
                LocalDate.now().plusDays(1),
                "cash");

        Contract contract2 = new Contract(services.get(2),
                "Cloe",
                "Williams",
                "6955000031",
                LocalDate.now().plusDays(2),
                "cash");

        Contract contract3 = new Contract(services.get(3),
                "Petros",
                "Papadopoulos",
                "6955431114",
                LocalDate.now(),
                "cash");

        contracts.add(contract);
        contracts.add(contract1);
        contracts.add(contract2);
        contracts.add(contract3);

    }


}
