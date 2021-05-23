package com.aeub.contracts;

import com.aeub.services.MobileInternetService;
import com.aeub.services.MobileTelephonyService;
import com.aeub.services.TelecomService;
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

    private String currentStatisticKey() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MMM"));
    }

    public void addOrUpdateStaticForCurrentMonth(Statistic statistic) {
        String key = currentStatisticKey();
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
        service.print();
        printStatisticsForCurrentMonth();
    }

    private void printStatisticsForCurrentMonth() {
        System.out.println("\tStatistics");
        statistics.getOrDefault(currentStatisticKey(), Statistic.Empty).print();
    }

    public boolean isActive() {
        return activation.isAfter(LocalDate.now());
    }

    // todo needs refactoring - place everything in right class.
    public double calculateTotalMonthlyCost() {
        double flatFee = this.service.getMonthlyFlatFee();
        double discountPercent = this.service.getDiscountPercentage();
        double flatFeeWithDiscount = flatFee * (1 - discountPercent);
        Statistic statistic = statistics.getOrDefault(currentStatisticKey(), Statistic.Empty);
        if(statistic == null){
            return 0.0;
        }
        long megabytesOfDataTransmitted = statistic.getMegabytesOfDataTransmitted();
        int smsSent = statistic.getNumberOfSmsSent();
        int minutesSpeechMobile = statistic.getSpeechTimeToMobileNetworks();
        int minutesSpeechOther =statistic.getSpeechTimeToMobileNetworks();

        if(this.service instanceof MobileTelephonyService) {
            MobileTelephonyService mobileTelephonyService = ((MobileTelephonyService)this.service);
            double smsCost = mobileTelephonyService.getSmsCost();
            int numberOfFreeSms = mobileTelephonyService.getFreeSMS();
            int freeSpeechMinutes = mobileTelephonyService.getFreeSpeechMinutes();
            double speechCostPerMinute = mobileTelephonyService.getSpeechCostPerMinute();

            double smsCostSent = 0;
            if(numberOfFreeSms - smsSent < 0) {
               smsCostSent = Math.abs(numberOfFreeSms - smsSent) * smsCost;
            }

            double speechCostTotal = 0;
            if(freeSpeechMinutes - (minutesSpeechMobile + minutesSpeechOther) < 0){
                speechCostTotal = Math.abs(freeSpeechMinutes - (minutesSpeechMobile + minutesSpeechOther)) * speechCostPerMinute;
            }
            return smsCostSent + speechCostTotal;
        }
        if(this.service instanceof MobileInternetService){
            MobileInternetService mobileInternetService = ((MobileInternetService)this.service);
            discountPercent = mobileInternetService.getDiscountPercentage();
            int numberOfFreeMegabytes = mobileInternetService.getFreeMegabytesForData();
            double costPerMegabyte = mobileInternetService.getCostPerMegabyteOfData();

            double costOfData = 0.0;
            if(numberOfFreeMegabytes - megabytesOfDataTransmitted < 0.0){
                costOfData = Math.abs(numberOfFreeMegabytes - megabytesOfDataTransmitted) * costPerMegabyte;
            }
            return costOfData;
        }
        return 0.0;
    }

    public double calculateRemainingBalance() {
        return 0;
    }

    public int calculateRemainingFreeSpeech() {
        return 0;
    }

    public int calculateRemainingFreeSms() {
        return 0;
    }

    public int calculateRemainingData() {
        return 0;
    }

    // ta statistica xrisis gia ka0e contract einai antistoixa me to programma tou sumbolaiou
}