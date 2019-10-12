package com.epam.fitness.dao.factory.impl;

import com.epam.fitness.builder.impl.*;
import com.epam.fitness.dao.api.*;
import com.epam.fitness.dao.factory.DaoFactory;
import com.epam.fitness.dao.impl.*;

import java.sql.Connection;

/**
 * The implementation of the dao factory specified
 * in the creation of sql-oriented dao classes
 */
public class DaoFactoryImpl implements DaoFactory {

    private Connection connection;

    public DaoFactoryImpl(Connection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection, new UserBuilder());
    }

    public OrderDao createOrderDao() {
        return new OrderDaoImpl(connection, new OrderBuilder());
    }

    public GymMembershipDao createGymMembershipDao() {
        return new GymMembershipDaoImpl(connection, new GymMembershipBuilder());
    }

    public ExerciseDao createExerciseDao() {
        return new ExerciseDaoImpl(connection, new ExerciseBuilder());
    }

    public AssignmentDao createAssignmentDao() {
        return new AssignmentDaoImpl(connection, new AssignmentBuilder());
    }

}