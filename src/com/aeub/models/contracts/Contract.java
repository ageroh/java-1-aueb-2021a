package com.aeub.models.contracts;

import com.aeub.models.services.TelecomService;
import com.aeub.telecomapp.Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Contract {
    private int code;
    private TelecomService service;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private LocalDate activation;
    private MethodOfPayment method;

    // statistics for each month, month is represented as 2019-Apr, 2021-May etc.
    private final HashMap<String, Statistic> statistics;

    public Contract(
            TelecomService service,
            String firstName,
            String lastName,
            String mobilePhone,
            LocalDate activation,
            MethodOfPayment methodOfPayment) {
        this.code = generateNewCode();
        this.service = service;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.activation = activation;
        this.method = methodOfPayment;
        statistics = new HashMap<>();
    }

    public Contract(ContractBuilder contractBuilder) {
        this.code = generateNewCode();
        this.service = contractBuilder.service;
        this.firstName = contractBuilder.firstName;
        this.lastName = contractBuilder.lastName;
        this.mobilePhone = contractBuilder.mobilePhone;
        this.activation = contractBuilder.activation;
        this.method = contractBuilder.methodOfPayment;
        statistics = new HashMap<>();
    }

    public void addOrUpdateStatic(LocalDate date, Statistic statistic) {
        String key = date.format(DateTimeFormatter.ofPattern("yyyy-MMM"));
        if (statistics.containsKey(key)) {
            statistics.put(key, statistics.get(key).append(statistic));
        } else {
            statistics.put(key, statistic);
        }
    }

    public int getCode() {
        return this.code;
    }

    // not thread safe
    private int generateNewCode() {
        if (Application.contracts.size() == 0) {
            return 1;
        }
        return Application.contracts.get(Application.contracts.size() - 1).getCode() + 1;
    }

    public TelecomService getTelecomService() {
        return service;
    }

    public void print() {
        System.out.println("Contract Code: " + code);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Mobile Phone: " + mobilePhone);
        System.out.println("Active Date: " + activation);
        System.out.println("Method of Payment: " + method.toString());
        System.out.println();
        service.print();
        printStatistics();
    }

    private void printStatistics() {
        System.out.println("\tStatistics");

    }

    public boolean isActive() {
        return activation.isAfter(LocalDate.now());
    }

    // ta statistica xrisis gia ka0e contract einai antistoixa me to programma tou sumbolaiou
}