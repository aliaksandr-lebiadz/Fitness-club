package com.epam.fitness.validator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class AssignmentValidatorTest {

    private static final int VALID_AMOUNT = 5;
    private static final int NEGATIVE_AMOUNT = -2;
    private static final int ZERO_AMOUNT = 0;

    private AssignmentValidator validator = new AssignmentValidator();

    /* testing isAmountValid method */

    @Test
    public void testIsAmountValidShouldReturnTrueWhenValidAmountSupplied(){
        //given

        //when
        boolean actual = validator.isAmountValid(VALID_AMOUNT);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsAmountValidShouldReturnFalseWhenNegativeAmountSupplied(){
        //given

        //when
        boolean actual = validator.isAmountValid(NEGATIVE_AMOUNT);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testIsAmountValidShouldReturnFalseWhenZeroAmountSupplied(){
        //given

        //when
        boolean actual = validator.isAmountValid(ZERO_AMOUNT);

        //then
        Assert.assertFalse(actual);
    }


    /* testing isWorkoutDateValid method */

    @Test
    public void testIsWorkoutDateValidShouldReturnTrueWhenValidDateSupplied(){
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date validDate = calendar.getTime();

        //when
        boolean actual = validator.isWorkoutDateValid(validDate);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsWorkoutDateValidShouldReturnFalseWhenInvalidDateSupplied(){
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date invalidDate = calendar.getTime();

        //when
        boolean actual = validator.isWorkoutDateValid(invalidDate);

        //then
        Assert.assertFalse(actual);
    }

}