package com.epam.fitness.validator.impl;

import com.epam.fitness.validator.api.PaymentValidator;

public class PaymentValidatorImpl implements PaymentValidator {

    private static final String CARD_NUMBER_REGEXP = "\\d{16}";
    private static final String EXPIRATION_DATE_REGEXP = "((0[1-9])|(1[0-2]))/\\d{2}";
    private static final String CVV_REGEXP = "\\d{3}";

    @Override
    public boolean isCardNumberValid(String cardNumber){
        return cardNumber.matches(CARD_NUMBER_REGEXP);
    }

    @Override
    public boolean isExpirationDateValid(String expirationDate){
        return expirationDate.matches(EXPIRATION_DATE_REGEXP);
    }

    @Override
    public boolean isCvvValid(String cvv){
        return cvv.matches(CVV_REGEXP);
    }

}