package com.gmail.evanloafakahaitao.pcstore.web.util;

public class OrderValidator {

    public boolean validateQuantity(String quantity) {
        if (quantity == null || quantity.equals("")) {
            return false;
        } else {
            return !Integer.valueOf(quantity).equals(0) && Integer.valueOf(quantity).compareTo(0) > 0 && Integer.valueOf(quantity).compareTo(1000) <= 0;
        }
    }
}
