package com.epam.fitness.service.impl;

import com.epam.fitness.dao.factory.DaoFactory;
import com.epam.fitness.entity.user.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.dao.api.UserDao;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(DaoFactory factory){
        userDao = factory.createUserDao();
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        try {
            String md5Password = DigestUtils.md5Hex(password);
            return userDao.findUserByEmailAndPassword(email, md5Password);
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<User> findUsersByTrainerId(int trainerId) throws ServiceException {
        try{
            return userDao.findUsersByTrainerId(trainerId);
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<User> getAllClients() throws ServiceException {
        try{
            return userDao.getAllClients();
        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserDiscount(int id, int discount) throws ServiceException {
        try{
            Optional<User> optionalUser = userDao.findById(id);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                user.setDiscount(discount);
                userDao.save(user);
            } else{
                throw new ServiceException("User with id " + id + " not found!");
            }

        } catch (DaoException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

}
