package com.aeub.models;

public enum MethodOfPayment {
    CASH(0), CREDITCARD(1), BANKTRANSFER(2);

    private final int value;
    private MethodOfPayment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
