package com.aeub.models.services;

public class MobileInternetService extends TelecomService {
    private int freeGigabytesForData;
    private int costPerGigabyteOfData;

    public MobileInternetService(int freeGigabytesForData, int costPerGigabyteOfData) {
        this.freeGigabytesForData = freeGigabytesForData;
        this.costPerGigabyteOfData = costPerGigabyteOfData;
        this.discountPercentage = 0.3;
    }
}
