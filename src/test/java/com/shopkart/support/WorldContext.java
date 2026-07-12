package com.shopkart.support;

import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.OrderPage;

public class WorldContext {

    private HomePage homePage;
    private CartPage cartPage;
    private OrderPage orderPage;

    private String productName;
    private String productSku;
    private String deliveryAddress;

    private String cartId;
    private String orderId;
    private String currentPersona;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(
            HomePage homePage
    ) {
        this.homePage = homePage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public void setCartPage(
            CartPage cartPage
    ) {
        this.cartPage = cartPage;
    }

    public OrderPage getOrderPage() {
        return orderPage;
    }

    public void setOrderPage(
            OrderPage orderPage
    ) {
        this.orderPage = orderPage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(
            String productName
    ) {
        this.productName = productName;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(
            String productSku
    ) {
        this.productSku = productSku;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(
            String deliveryAddress
    ) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(
            String cartId
    ) {
        this.cartId = cartId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(
            String orderId
    ) {
        this.orderId = orderId;
    }
    public String getCurrentPersona() {
        return currentPersona;
    }

    public void setCurrentPersona(
            String currentPersona
    ) {
        this.currentPersona = currentPersona;
    }
}