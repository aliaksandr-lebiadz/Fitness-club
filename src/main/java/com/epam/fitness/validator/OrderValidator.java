package com.epam.fitness.validator;

public class OrderValidator {

    private static final int MIN_FEEDBACK_LENGTH = 10;
    private static final int MAX_FEEDBACK_LENGTH = 1000;

    public boolean isFeedbackValid(String feedback){
        int length = feedback.length();
        return length >= MIN_FEEDBACK_LENGTH && length <= MAX_FEEDBACK_LENGTH;
    }

}
