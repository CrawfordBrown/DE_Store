package com.destore.application;

import com.destore.model.ShoppingCart;

public interface iPriceControlController {
    void setProductPrice(int productId, double newPrice);

    void apply3For2Offer(ShoppingCart shoppingCart);

    void applyBuyOneGetOneFreeOffer(ShoppingCart shoppingCart);
}