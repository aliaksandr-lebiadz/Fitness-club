package com.epam.fitness.validator;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class AssignmentValidator {

    private static final int MIN_AMOUNT = 1;

    public boolean isAmountValid(int amount){
        return amount >= MIN_AMOUNT;
    }

    public boolean isWorkoutDateValid(Date workoutDate){
        Date now = new Date();
        Date today = DateUtils.truncate(now, Calendar.DAY_OF_MONTH);
        return !workoutDate.before(today);
    }

}