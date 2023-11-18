package com.destore.application;
// Implementation in the application layer

import com.destore.business.iPriceControlService;
import com.destore.model.ShoppingCart;

public class PriceControlController implements iPriceControlController{

    private final iPriceControlService priceControlService;

    public PriceControlController(iPriceControlService priceControlService) {
        this.priceControlService = priceControlService;
    }

    @Override
    public void setProductPrice(int productId, double newPrice) {
        priceControlService.setProductPrice(productId, newPrice);
    }

    @Override
    public void apply3For2Offer(ShoppingCart shoppingCart) {
        priceControlService.apply3For2Offer(shoppingCart);
    }

    @Override
    public void applyBuyOneGetOneFreeOffer(ShoppingCart shoppingCart) {
        priceControlService.applyBuyOneGetOneFreeOffer(shoppingCart);
    }
}
