package com.epam.fitness.dao.factory;

import com.epam.fitness.dao.api.*;

/**
 * Factory for the dao creation
 */
public interface DaoFactory {

    UserDao createUserDao();
    OrderDao createOrderDao();
    GymMembershipDao createGymMembershipDao();
    ExerciseDao createExerciseDao();
    AssignmentDao createAssignmentDao();
}