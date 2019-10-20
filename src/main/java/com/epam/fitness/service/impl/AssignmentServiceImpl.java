package com.epam.fitness.service.impl;

import com.epam.fitness.dao.factory.DaoFactory;
import com.epam.fitness.entity.order.NutritionType;
import com.epam.fitness.entity.order.Order;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.dao.api.OrderDao;
import com.epam.fitness.dao.api.AssignmentDao;
import com.epam.fitness.entity.assignment.Assignment;
import com.epam.fitness.entity.assignment.AssignmentStatus;
import com.epam.fitness.entity.assignment.Exercise;
import com.epam.fitness.service.api.AssignmentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AssignmentServiceImpl implements AssignmentService {

    private AssignmentDao assigmentDao;
    private OrderDao orderDao;

    public AssignmentServiceImpl(DaoFactory factory){
        assigmentDao = factory.createAssignmentDao();
        orderDao = factory.createOrderDao();
    }


    @Override
    public void create(Assignment assignment) throws ServiceException {
        try{
            assigmentDao.save(assignment);
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Assignment> getAllByOrderId(int orderId) throws ServiceException {
        try{
            return assigmentDao.getAllByOrderId(orderId);
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public String getNutritionTypeByOrderId(int orderId) throws ServiceException {
        try{
            Optional<Order> orderOptional = orderDao.findById(orderId);
            if(orderOptional.isPresent()){
                Order order = orderOptional.get();
                NutritionType nutritionType = order.getNutritionType();
                return nutritionType != null ? nutritionType.getValue() : null;
            } else{
                throw new ServiceException("Order with id " + orderId + " not found!");
            }
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void changeStatusById(int id, AssignmentStatus status) throws ServiceException {
        try{
            Optional<Assignment> optionalAssignment = assigmentDao.findById(id);
            if(optionalAssignment.isPresent()){
                Assignment assignment = optionalAssignment.get();
                assignment.setStatus(status);
                assigmentDao.save(assignment);
            } else{
                throw new ServiceException("Assignment with id " + id + " not found!");
            }
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateById(int id, Exercise exercise, int amountOfSets, int amountOfReps, Date workoutDate) throws ServiceException {
        try{
            Optional<Assignment> optionalAssignment = assigmentDao.findById(id);
            if(optionalAssignment.isPresent()){
                Assignment assignment = optionalAssignment.get();
                assignment.setExercise(exercise);
                assignment.setAmountOfSets(amountOfSets);
                assignment.setAmountOfReps(amountOfReps);
                assignment.setWorkoutDate(workoutDate);
                assignment.setStatus(AssignmentStatus.CHANGED);
                assigmentDao.save(assignment);
            }
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Assignment> findById(int id) throws ServiceException {
        try{
            return assigmentDao.findById(id);
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}