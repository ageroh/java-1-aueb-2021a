package com.aueb;

public abstract class MobileTelephonyService extends TelecomService {
    protected int freeSpeechMinutes;
    protected int freeSMS;
    protected double speechCostPerMinute;
    protected double smsCost;

    public double getSmsCost(){
        return this.smsCost;
    }

    public double getSpeechCostPerMinute(){
        return this.speechCostPerMinute;
    }

    public int getFreeSpeechMinutes(){
        return this.freeSpeechMinutes;
    }

    public int getFreeSMS(){
        return this.freeSMS;
    }
}
