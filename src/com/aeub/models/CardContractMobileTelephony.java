package com.aeub.models;

public class CardContractMobileTelephony extends MobileTelephony {
    private int monthlyBalanceEuros;

    public CardContractMobileTelephony(int monthlyBalanceEuros) {
        this.monthlyBalanceEuros = monthlyBalanceEuros;
        this.discountPercentage = 0.25;
    }
}
