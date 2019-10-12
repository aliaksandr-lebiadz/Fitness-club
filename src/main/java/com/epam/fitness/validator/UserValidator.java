package com.epam.fitness.validator;

public class UserValidator {

    private static final int MIN_DISCOUNT = 0;
    private static final int MAX_DISCOUNT = 100;

    public boolean isDiscountValid(int discount){
        return discount >= MIN_DISCOUNT && discount <= MAX_DISCOUNT;
    }

}