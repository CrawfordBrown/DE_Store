package com.destore.model;

import java.util.Date;

public class LoyaltyCard {
    private int loyaltyCardId;
    private int customerId;
    private int points;
    private Date date;

    public int getLoyaltyCardId() {
        return loyaltyCardId;
    }

    public void setLoyaltyCardId(int loyaltyCardId) {
        this.loyaltyCardId = loyaltyCardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
