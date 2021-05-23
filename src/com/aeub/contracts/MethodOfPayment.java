package com.aeub.contracts;

public enum MethodOfPayment {
    CASH("CASH", 0),
    CREDIT_CARD("CREDIT CARD", 1),
    BANK_TRANSFER("BANK TRANSFER", 2);

    private final int value;
    private final String name;

    private MethodOfPayment(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() { return name; }
}
