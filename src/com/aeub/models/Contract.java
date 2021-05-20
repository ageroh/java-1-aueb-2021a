package com.aeub.models;

import com.aeub.Main;
import com.aeub.models.services.TelecomService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Contract {
    private int code;
    private TelecomService service;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private LocalDate activation;
    private String methodOfPayment;

    // statistics for each month, month is represented as 2019-Apr, 2021-May etc.
    private HashMap<String, Statistic> statistics;

    public Contract(
            TelecomService service,
            String firstName,
            String lastName,
            String mobilePhone,
            LocalDate activation,
            String methodOfPayment) {
        this.code = generateNewCode();
        this.service = service;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.activation = activation;
        this.methodOfPayment = methodOfPayment;
        statistics = new HashMap<>();
    }

    public void setExtraDiscountPercentage(double extraDiscountPercentage){
        //    this.service. ?
    }

    public void addOrUpdateStatic(LocalDate date, Statistic statistic) {
        String key = date.format(DateTimeFormatter.ofPattern("yyyy-MMM"));
        if (statistics.containsKey(key)) {
            statistics.get(key).append(statistic);
        } else {
            statistics.put(key, statistic);
        }
    }

    private int getCode(){
        return this.code;
    }

    // not thread safe
    private int generateNewCode(){
        return Main.contracts.get(Main.contracts.size() - 1).getCode() + 1;
    }

    // Τα στατιστικά χρήσης για κάθε συμβόλαιο είναι αντίστοιχα με το πρόγραμμα του συμβολαίου.

}
