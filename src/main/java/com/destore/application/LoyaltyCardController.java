package com.destore.application;

import com.destore.business.LoyaltyCardService;
import com.destore.data.LoyaltyCardDAO;
import com.destore.model.LoyaltyCard;

import java.util.List;

public class LoyaltyCardController implements iLoyaltyCardController {
    private final LoyaltyCardService loyaltyCardService;

    public LoyaltyCardController(LoyaltyCardService loyaltyCardService) {
        this.loyaltyCardService = loyaltyCardService;
    }
    public LoyaltyCardController(LoyaltyCardDAO loyaltyCardDAO) {
        this.loyaltyCardService = new LoyaltyCardService(loyaltyCardDAO);
    }

    public List<LoyaltyCard> getAllLoyaltyCards() {
        return loyaltyCardService.getAllLoyaltyCards();
    }

    public boolean applyBOGOF(int loyaltyPoints) {
        return loyaltyCardService.applyBOGOF(loyaltyPoints);
    }

    public boolean apply3For2(int loyaltyPoints) {
        return loyaltyCardService.apply3For2(loyaltyPoints);
    }
}
