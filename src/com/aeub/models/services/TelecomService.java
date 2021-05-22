package com.aeub.models.services;

public abstract class TelecomService {
    protected String nameOfService;
    protected int monthlyFlatFee;           // pagio
    protected double discountPercentage;    // todo this could be BigDecimal.

    public abstract void print();

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
