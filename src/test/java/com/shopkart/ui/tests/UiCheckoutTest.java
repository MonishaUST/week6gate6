package com.shopkart.ui.tests;

import com.shopkart.support.BaseUiTest;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.OrderPage;
import org.junit.jupiter.api.Test;

public class UiCheckoutTest extends BaseUiTest {

    @Test
    void aliceCanCheckoutBag() {

        String productName =
                "Metro Carryall";

        String productSku =
                "SKU-BAG";

        String deliveryAddress =
                "UST Campus, Trivandrum, Kerala";


        HomePage homePage =
                new HomePage()
                        .openPage();


        homePage
                .header()
                .clickSignIn()
                .loginAs("alice");


        homePage
                .searchFor(productName)
                .product(productName)
                .shouldBeVisible()
                .addToCart();


        CartPage cartPage =
                homePage
                        .header()
                        .openCart();


        cartPage
                .shouldContainProduct(productSku)
                .shouldShowCartTotal();


        OrderPage orderPage =
                cartPage
                        .checkout()
                        .enterAddress(deliveryAddress)
                        .placeOrder();


        orderPage
                .shouldShowOrderNumber()
                .shouldShowOrderStatus()
                .shouldShowOrderTotal()
                .shouldShowDeliveryAddress(
                        deliveryAddress
                );
    }
}