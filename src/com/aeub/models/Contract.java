package com.aeub.models;

import java.util.Date;
import java.util.Map;

public class Contract {
    private int code; // mοναδικός και αύξων αριθμός?
    private TelecomService service;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private Date activation;
    private String methodOfPayment;
    private double extraDiscountPercentage;

    // statistics
    private Map<Date, Statistic> Statistics;

    /**
    Επίσης, για κάθε συμβόλαιο διατηρούνται μηνιαία στατιστικά χρήσης
        - όπως χρόνος ομιλίας προς κινητά δίκτυα,
        - χρόνος ομιλίας προς σταθερά δίκτυα
        - αριθμό μηνυμάτων SMS που στάλθηκαν
        - όγκος κίνησης που μεταδόθηκε.
     Τα στατιστικά χρήσης για κάθε συμβόλαιο είναι αντίστοιχα με το πρόγραμμα του συμβολαίου.
    */
}
