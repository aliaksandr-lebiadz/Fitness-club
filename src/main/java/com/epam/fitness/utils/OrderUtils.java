package com.epam.fitness.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class OrderUtils {

    private static final double MAX_DISCOUNT = 100.0;

    public Date getDateAfterMonthsAmount(int monthsAmount){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthsAmount);
        return calendar.getTime();
    }

    public BigDecimal getPriceWithDiscount(BigDecimal initialPrice, int discount){
        double discountInRatio = (MAX_DISCOUNT - discount) / MAX_DISCOUNT;
        return initialPrice.multiply(new BigDecimal(discountInRatio));
    }

}