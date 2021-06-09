package com.aeub;

public abstract class TelecomService {
    protected String nameOfService;
    protected int monthlyFlatFee;           // pagio
    protected double discountPercentage;

    public void print(){
        System.out.println("\tCard Contract Service Details");
        System.out.println("\tName of Service:      :" + nameOfService);
        System.out.println("\tMonthly Flat Fee      :" + monthlyFlatFee);
        System.out.println("\tDiscount Percentage   :" + discountPercentage);
    }

    public String getNameOfService() {
        return nameOfService;
    }

    public int getMonthlyFlatFee() {
        return monthlyFlatFee;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    // todo this should be done in more oo way.
    public int getServiceId() {
        if (this instanceof MobileInternetService) {
            return 0;
        } else if (this instanceof CardContractService) {
            return 1;
        } else {
            return 2;
        }
    }
}
