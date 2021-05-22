package com.aeub.models.services;

public final class ContractService extends MobileTelephonyService {
    public ContractService() {
    }

    public ContractService(
            String nameOfService,
            int monthlyFlatFee,
            double discountPercentage,
            int freeSMS,
            int freeSpeechMinutes,
            double smsCost,
            double speechCostPerMinute) {
        this.nameOfService = nameOfService;
        this.monthlyFlatFee = monthlyFlatFee;
        this.discountPercentage = discountPercentage;   // 0.2
        this.freeSMS = freeSMS;
        this.freeSpeechMinutes = freeSpeechMinutes;
        this.smsCost = smsCost;
        this.speechCostPerMinute = speechCostPerMinute;
    }

    @Override
    public void print() {
        System.out.println("\tContract Service Details");
        // todo
    }
}
