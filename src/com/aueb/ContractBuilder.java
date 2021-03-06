package com.aueb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.aueb.Utils.tryLoop;

public class ContractBuilder {
    public TelecomService service;
    public String firstName;
    public String lastName;
    public String mobilePhone;
    public LocalDate activation;
    public MethodOfPayment methodOfPayment;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ContractBuilder activationDate(Scanner in) {
        this.activation = tryLoop(() -> {
            System.out.print("Enter service Activation Date(dd/MM/yyyy):");
            String dateStr = in.next();
            return LocalDate.parse(dateStr, formatter);
        }, "Date format should be: dd/MM/yyyy");
        return this;
    }

    public ContractBuilder initContractService(int contractTypeId) {
        TelecomService service = resolveServiceByType(contractTypeId);
        this.service = service;
        return this;
    }

    public ContractBuilder addFirstName(Scanner in) {
        this.firstName = tryLoop(() -> {
            System.out.print("Enter client First name:");
            String firstName = in.nextLine();
            validateString(firstName);
            return firstName;
        }, "Provide correct First Name (3-30 chars with no spaces)");
        return this;
    }

    private void validateString(String firstName) throws Exception {
        if(firstName == null ||
                firstName.length() < 3 ||
                firstName.length() > 30 ) { //||  firstName.isBlank()){
            throw new Exception();
        }
    }

    public ContractBuilder addLastName(Scanner in) {
        this.lastName = tryLoop(() -> {
            System.out.print("Enter client Last name:");
            String lastName = in.nextLine();
            validateString(lastName);
            return lastName;
        }, "Provide correct Last Name (3-30 chars with no spaces)");
        return this;
    }

    public ContractBuilder addMobilePhone(Scanner in) {
        this.mobilePhone = tryLoop(() -> {
            System.out.print("Enter Mobile Phone:");
            String mobilePhone = in.nextLine();
            if(!mobilePhone.matches("^\\d{10}$")){
                throw new Exception();
            }
            return mobilePhone;
        }, "Mobile phone should be 10 chars.");
        return this;
    }

    public ContractBuilder addMethodOfPayment(Scanner in) {
        this.methodOfPayment = tryLoop(() -> {
            System.out.println("Pick a method of payment:");
            System.out.println("1 - Cash");
            System.out.println("2 - Credit Card");
            System.out.println("3 - Bank Transfer");
            return  MethodOfPayment.values()[in.nextInt()];
        }, "Pick one of 1,2 or 3");
        return this;
    }

    // todo remove this one.
    private TelecomService resolveServiceByType(int selection) {
        switch (selection){
            case 1: return new ContractService();
            case 2: return  new CardContractService();
            case 3: return new MobileInternetService();
            default: return null;
        }
    }

    public Contract build() {
        Contract contract = new Contract(this);
        return  contract;
    }
}
