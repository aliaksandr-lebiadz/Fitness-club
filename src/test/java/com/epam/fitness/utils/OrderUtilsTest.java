package com.epam.fitness.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;

public class OrderUtilsTest {

    private static final double DOUBLE_DELTA = 1e-7;

    private OrderUtils utils = new OrderUtils();

    @Test
    public void testGetPriceWithDiscountShouldReturnSamePriceWhenZeroSupplied(){
        //given
        final int discount = 0;
        final BigDecimal initialPrice = new BigDecimal(2.55);
        final BigDecimal expected = new BigDecimal(2.55);

        //when
        BigDecimal actual = utils.getPriceWithDiscount(initialPrice, discount);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPriceWithDiscountShouldReturnZeroWhenOneHundredPercentDiscountSupplied(){
        //given
        final int discount = 100;
        final BigDecimal initialPrice = new BigDecimal(52.1);
        final double expected = 0.0;

        //when
        BigDecimal actual = utils.getPriceWithDiscount(initialPrice, discount);

        //then
        Assert.assertEquals(expected, actual.doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void testGetPriceWithDiscountShouldReturnHalfOfPriceWhenFiftyPercentDiscountSupplied(){
        //given
        final int discount = 50;
        final BigDecimal initialPrice = new BigDecimal(11.77);
        final double expected = 5.885;

        //when
        BigDecimal actual = utils.getPriceWithDiscount(initialPrice, discount);

        //then
        Assert.assertEquals(expected, actual.doubleValue(), DOUBLE_DELTA);
    }

}