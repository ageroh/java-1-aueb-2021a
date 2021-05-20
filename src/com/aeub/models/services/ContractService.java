package com.aeub.models.services;

public class ContractService extends MobileTelephonyService {

    public ContractService() {
        this.discountPercentage = 0.2;
        this.freeSMS = 100;
        this.freeSpeechMinutes = 120;
        this.smsCost = 0.003;
        this.speechCostPerMinute = 0.02;
    }

    public ContractService(
            double discountPercentage,
            int freeSMS,
            int freeSpeechMinutes,
            double smsCost,
            double speechCostPerMinute) {
        this.discountPercentage = discountPercentage;
        this.freeSMS = freeSMS;
        this.freeSpeechMinutes = freeSpeechMinutes;
        this.smsCost = smsCost;
        this.speechCostPerMinute = speechCostPerMinute;
    }

}
