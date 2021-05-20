package com.aeub.models.services;

public abstract class MobileTelephonyService extends TelecomService {
    protected int freeSpeechMinutes;
    protected int freeSMS;
    protected double speechCostPerMinute;
    protected double smsCost;
}
