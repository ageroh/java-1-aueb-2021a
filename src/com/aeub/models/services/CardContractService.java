package com.aeub.models.services;

public class CardContractService extends MobileTelephonyService {

    private int monthlyBalanceEuros;

    public int getMonthlyBalanceEuros() {
        return monthlyBalanceEuros;
    }

    public void setMonthlyBalanceEuros(int monthlyBalanceEuros) {
        this.monthlyBalanceEuros = monthlyBalanceEuros;
    }

    public CardContractService(int monthlyBalanceEuros) {
        this.monthlyBalanceEuros = monthlyBalanceEuros;
        this.discountPercentage = 0.25;
        this.freeSMS = 100;
        this.freeSpeechMinutes = 100;
        this.smsCost = 0.005;
        this.speechCostPerMinute = 0.01;
    }

    public CardContractService(
            int monthlyBalanceEuros,
            int freeSMS,
            int freeSpeechMinutes,
            double smsCost,
            double speechCostPerMinute) {
        this.monthlyBalanceEuros = monthlyBalanceEuros;
        this.discountPercentage = 0.25;
        this.freeSMS = freeSMS;
        this.freeSpeechMinutes = freeSpeechMinutes;
        this.smsCost = smsCost;
        this.speechCostPerMinute = speechCostPerMinute;
    }
}
