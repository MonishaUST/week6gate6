package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;
import static com.shopkart.ui.locators.OrderLocators.DELIVERY_ADDRESS;
import static com.shopkart.ui.locators.OrderLocators.ORDER_NUMBER;
import static com.shopkart.ui.locators.OrderLocators.ORDER_STATUS;
import static com.shopkart.ui.locators.OrderLocators.ORDER_TOTAL;

public class OrderPage {

    public OrderPage shouldShowOrderNumber() {

        $x(ORDER_NUMBER)
                .shouldBe(Condition.visible)
                .shouldNotBe(Condition.empty);

        return this;
    }

    public OrderPage shouldShowOrderStatus() {

        $x(ORDER_STATUS)
                .shouldBe(Condition.visible)
                .shouldNotBe(Condition.empty);

        return this;
    }

    public OrderPage shouldShowOrderTotal() {

        $x(ORDER_TOTAL)
                .shouldBe(Condition.visible)
                .shouldNotBe(Condition.empty);

        return this;
    }

    public OrderPage shouldShowDeliveryAddress(
            String address
    ) {

        $x(DELIVERY_ADDRESS)
                .shouldBe(Condition.visible)
                .shouldHave(
                        Condition.text(address)
                );

        return this;
    }
    public OrderPage shouldHaveStatus(
            String expectedStatus
    ) {

        $x(ORDER_STATUS)
                .shouldBe(Condition.visible)
                .shouldHave(
                        Condition.exactText(
                                expectedStatus
                        )
                );

        return this;
    }
    public String getOrderNumberText() {

        return $x(ORDER_NUMBER)
                .shouldBe(Condition.visible)
                .getText();
    }

    public String getOrderId() {

        String orderText =
                getOrderNumberText();

        String orderId =
                orderText.replaceAll(
                        "\\D+",
                        ""
                );

        if (orderId.isBlank()) {

            throw new IllegalStateException(
                    "Order ID was not found in: "
                            + orderText
            );
        }

        return orderId;
    }
}