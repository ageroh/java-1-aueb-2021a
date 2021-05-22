package com.aeub.models.contracts;

public class Statistic {
    private int speechTimeToMobileNetworks;
    private int speechTimeToOtherNetworks;
    private int numberOfSmsSent;
    private long megabytesOfDataTransmitted;

    public void setSpeechTimeToMobileNetworks(int speechTimeToMobileNetworks) {
        this.speechTimeToMobileNetworks = speechTimeToMobileNetworks;
    }

    public void setSpeechTimeToOtherNetworks(int speechTimeToOtherNetworks) {
        this.speechTimeToOtherNetworks = speechTimeToOtherNetworks;
    }

    public void setNumberOfSmsSent(int numberOfSmsSent) {
        this.numberOfSmsSent = numberOfSmsSent;
    }

    public void setMegabytesOfDataTransmitted(long megabytesOfDataTransmitted) {
        this.megabytesOfDataTransmitted = megabytesOfDataTransmitted;
    }

    public int getSpeechTimeToMobileNetworks() {
        return speechTimeToMobileNetworks;
    }

    public int getSpeechTimeToOtherNetworks() {
        return speechTimeToOtherNetworks;
    }

    public int getNumberOfSmsSent() {
        return numberOfSmsSent;
    }

    public long getMegabytesOfDataTransmitted() {
        return megabytesOfDataTransmitted;
    }

    public Statistic append(Statistic statistic) {
        speechTimeToMobileNetworks =+ statistic.speechTimeToMobileNetworks;
        speechTimeToOtherNetworks =+ statistic.speechTimeToOtherNetworks;
        numberOfSmsSent =+ statistic.numberOfSmsSent;
        megabytesOfDataTransmitted =+ statistic.megabytesOfDataTransmitted;
        return this;
    }
}
