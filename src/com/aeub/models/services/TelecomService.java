package com.aeub.models.services;

public abstract class TelecomService implements ITelecomService {
    protected String nameOfService;
    protected int monthlyFlatFee;   // pagio
    protected double discountPercentage;    // todo this could be BigDecimal.
}
