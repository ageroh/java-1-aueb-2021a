package com.aeub.models.services;

public final class CardContractService extends MobileTelephonyService {

    private int monthlyBalanceEuros;

    public int getMonthlyBalanceEuros() {
        return monthlyBalanceEuros;
    }

    public void setMonthlyBalanceEuros(int monthlyBalanceEuros) {
        this.monthlyBalanceEuros = monthlyBalanceEuros;
    }

    public CardContractService() {
    }

    public CardContractService(
            String nameOfService,
            int monthlyFlatFee,
            int monthlyBalanceEuros,
            double discountPercentage,
            int freeSMS,
            int freeSpeechMinutes,
            double smsCost,
            double speechCostPerMinute) {
        this.nameOfService = nameOfService;
        this.monthlyFlatFee = monthlyFlatFee;
        this.monthlyBalanceEuros = monthlyBalanceEuros;
        this.discountPercentage = discountPercentage; // 0.25;
        this.freeSMS = freeSMS;
        this.freeSpeechMinutes = freeSpeechMinutes;
        this.smsCost = smsCost;
        this.speechCostPerMinute = speechCostPerMinute;
    }

    @Override
    public void print() {
        System.out.println("\tCard Contract Service Details");
        // todo
    }
}
