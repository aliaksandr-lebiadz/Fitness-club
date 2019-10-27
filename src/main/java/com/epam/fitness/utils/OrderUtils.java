package com.epam.fitness.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Utils class for operations, related with an
 * {@link com.epam.fitness.entity.order.Order} entity.</p>
 *
 * @author Alexandr Lebed
 */
public class OrderUtils {

    /**
     * <p>A maximal percentage discount.</p>
     */
    private static final double MAX_DISCOUNT = 100.0;

    /**
     * <p>Calculate a {@link Date} after the number of months supplied.</p>
     *
     * @param monthsAmount a number of months
     * @return the {@link Date} after the supplied months amount
     */
    public Date getDateAfterMonthsAmount(int monthsAmount){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthsAmount);
        return calendar.getTime();
    }

    /**
     * <p>Calculates a new price according to the supplied discount.</p>
     *
     * @param initialPrice an initial price
     * @param discount an percentage discount
     * @return the price with discount
     */
    public BigDecimal calculatePriceWithDiscount(BigDecimal initialPrice, int discount){
        double discountRateValue = (MAX_DISCOUNT - discount) / MAX_DISCOUNT;
        BigDecimal discountRate = new BigDecimal(discountRateValue);
        return initialPrice.multiply(discountRate);
    }

}