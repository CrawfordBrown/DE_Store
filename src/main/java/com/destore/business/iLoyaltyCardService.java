package com.destore.business;

import com.destore.model.LoyaltyCard;

import java.util.List;

public interface iLoyaltyCardService {

    List<LoyaltyCard> getAllLoyaltyCards();


    boolean applyBOGOF(int loyaltyPoints);

    boolean apply3For2(int loyaltyPoints);
}
