package com.aeub;

public enum MethodOfPayment {
    CASH("CASH", 1),
    CREDIT_CARD("CREDIT CARD", 2),
    BANK_TRANSFER("BANK TRANSFER", 3);

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
