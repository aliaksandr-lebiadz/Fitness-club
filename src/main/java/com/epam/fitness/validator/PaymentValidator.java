package com.epam.fitness.validator;

public class PaymentValidator {

    private static final String CARD_NUMBER_REGEXP = "\\d{16}";
    private static final String VALID_THRU_REGEXP = "((0[1-9])|(1[0-2]))/\\d{2}";
    private static final String CVV_REGEXP = "\\d{3}";

    public boolean isCardNumberValid(String cardNumber){
        return cardNumber.matches(CARD_NUMBER_REGEXP);
    }

    public boolean isThruValid(String validThru){
        return validThru.matches(VALID_THRU_REGEXP);
    }

    public boolean isCvvValid(String cvv){
        return cvv.matches(CVV_REGEXP);
    }

}
