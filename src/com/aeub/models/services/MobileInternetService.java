package com.aeub.models.services;

public final class MobileInternetService extends TelecomService {
    private int freeGigabytesForData;
    private double costPerGigabyteOfData;

    public MobileInternetService() {
    }

    public MobileInternetService(
            String nameOfService,
            int monthlyFlatFee,
            int freeGigabytesForData,
            double costPerGigabyteOfData,
            double discountPercentage) {
        this.freeGigabytesForData = freeGigabytesForData;
        this.costPerGigabyteOfData = costPerGigabyteOfData;
        this.discountPercentage = discountPercentage; //0.3;
        this.nameOfService = nameOfService;
        this.monthlyFlatFee = monthlyFlatFee;
    }

    @Override
    public void print() {
        System.out.println("\tMobile Internet Service Details");
        // todo
    }
}
