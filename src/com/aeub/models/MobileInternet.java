package com.aeub.models;

public class MobileInternet extends TelecomService {
    private int freeGigabytesForData;
    private int costPerGigabyteOfData;

    public MobileInternet(int freeGigabytesForData, int costPerGigabyteOfData) {
        this.freeGigabytesForData = freeGigabytesForData;
        this.costPerGigabyteOfData = costPerGigabyteOfData;
        this.discountPercentage = 0.3;
    }
}
