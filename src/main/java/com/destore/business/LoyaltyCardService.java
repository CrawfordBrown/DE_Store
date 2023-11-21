package com.destore.business;

import com.destore.data.LoyaltyCardDAO;
import com.destore.model.LoyaltyCard;

import java.util.List;

public class LoyaltyCardService implements iLoyaltyCardService{
        private final LoyaltyCardDAO loyaltyCardDAO;

        public LoyaltyCardService(LoyaltyCardDAO loyaltyCardDAO) {
            this.loyaltyCardDAO = loyaltyCardDAO;
        }

    @Override
        public List<LoyaltyCard> getAllLoyaltyCards() {
            return loyaltyCardDAO.getAllLoyaltyCards();
        }


    @Override
        public boolean applyBOGOF(int loyaltyPoints) {
            return loyaltyPoints >= 100;
        }

    @Override
    public boolean apply3For2(int loyaltyPoints) {
            return loyaltyPoints >= 50 && loyaltyPoints < 100;
        }
    }

