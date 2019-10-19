package com.epam.fitness.validator;

import org.junit.Assert;
import org.junit.Test;

public class OrderValidatorTest {

    private static final String VALID_FEEDBACK = "Hello. It's my feedback.";
    private static final String INVALID_FEEDBACK = "Feedback.";

    private OrderValidator validator = new OrderValidator();

    @Test
    public void testIsFeedbackValidShouldReturnTrueWhenValidFeedbackSupplied(){
        //given

        //when
        boolean actual = validator.isFeedbackValid(VALID_FEEDBACK);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsFeedbackValidShouldReturnFalseWhenInvalidFeedbackSupplied(){
        //given

        //when
        boolean actual = validator.isFeedbackValid(INVALID_FEEDBACK);

        //then
        Assert.assertFalse(actual);
    }

}