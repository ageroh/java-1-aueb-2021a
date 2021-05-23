package com.aeub.contracts;

public class Statistic {
    public static Statistic Empty = new Statistic();
    private int speechTimeToMobileNetworks;
    private int speechTimeToOtherNetworks;
    private int numberOfSmsSent;
    private long megabytesOfDataTransmitted;

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

    public Statistic(
            int speechTimeToMobileNetworks,
            int speechTimeToOtherNetworks,
            int numberOfSmsSent,
            long megabytesOfDataTransmitted) {
        this.speechTimeToMobileNetworks = speechTimeToMobileNetworks;
        this.speechTimeToOtherNetworks = speechTimeToOtherNetworks;
        this.numberOfSmsSent = numberOfSmsSent;
        this.megabytesOfDataTransmitted = megabytesOfDataTransmitted;
    }

    private Statistic() {
    }

    public Statistic append(Statistic statistic) {
        speechTimeToMobileNetworks = +statistic.speechTimeToMobileNetworks;
        speechTimeToOtherNetworks = +statistic.speechTimeToOtherNetworks;
        numberOfSmsSent = +statistic.numberOfSmsSent;
        megabytesOfDataTransmitted = +statistic.megabytesOfDataTransmitted;
        return this;
    }

    public void print() {
        System.out.println("\t\tMobile Data usage       :" + megabytesOfDataTransmitted);
        System.out.println("\t\tSpeech Minutes to Mobile:" + speechTimeToMobileNetworks);
        System.out.println("\t\tSpeech Minutes to Other :" + speechTimeToOtherNetworks);
        System.out.println("\t\tSMS sent                :" + numberOfSmsSent);
    }
}
