package com.aeub.services;

public final class CardContractService extends MobileTelephonyService {

    private double monthlyBalanceEuros;

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
        super.print();
        System.out.println("\tMonthly Balance Euros :" + monthlyBalanceEuros);
        System.out.println("\tFree Number of SMS    :" + freeSMS);
        System.out.println("\tFree Speech Minutes   :" + freeSpeechMinutes);
        System.out.println("\tCost per SMS          :" + smsCost);
        System.out.println("\tSpeech Cost / Minute  :" + speechCostPerMinute);
        System.out.println();
    }

}
