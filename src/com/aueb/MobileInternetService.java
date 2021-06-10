package com.aueb;

public final class MobileInternetService extends TelecomService {
    private int freeMegabytesForData;
    private double costPerMegabyteOfData;

    public int getFreeMegabytesForData() {
        return freeMegabytesForData;
    }

    public double getCostPerMegabyteOfData() {
        return costPerMegabyteOfData;
    }

    public MobileInternetService() {
    }

    public MobileInternetService(
            String nameOfService,
            int monthlyFlatFee,
            int freeGigabytesForData,
            double costPerGigabyteOfData,
            double discountPercentage) {
        this.freeMegabytesForData = freeGigabytesForData;
        this.costPerMegabyteOfData = costPerGigabyteOfData;
        this.discountPercentage = discountPercentage; //0.3;
        this.nameOfService = nameOfService;
        this.monthlyFlatFee = monthlyFlatFee;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\tNumber of Free MB     :" + freeMegabytesForData);
        System.out.println("\tCost per MB of Data   :" + costPerMegabyteOfData);
        System.out.println();
    }
}
