package com.destore.business;

import com.destore.model.ShoppingCart;

public interface iPriceControlService {

    void setProductPrice(int productId, double newPrice);

    void apply3For2Offer(ShoppingCart shoppingCart);

    void applyBuyOneGetOneFreeOffer(ShoppingCart shoppingCart);

}
