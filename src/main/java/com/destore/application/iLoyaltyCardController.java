package com.destore.application;

import com.destore.model.LoyaltyCard;

import java.util.List;

public interface iLoyaltyCardController {

    List<LoyaltyCard> getAllLoyaltyCards();

    boolean applyBOGOF(int loyaltyPoints);

    boolean apply3For2(int loyaltyPoints);
}
