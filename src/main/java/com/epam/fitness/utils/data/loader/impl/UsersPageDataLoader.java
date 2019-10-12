package com.epam.fitness.utils.data.loader.impl;

import com.epam.fitness.entity.user.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.UserService;
import com.epam.fitness.utils.data.loader.PageDataLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersPageDataLoader implements PageDataLoader {

    private static final String USERS_ATTRIBUTE = "users";

    private UserService service;

    public UsersPageDataLoader(UserService service){
        this.service = service;
    }

    @Override
    public void loadDataToSession(HttpServletRequest request) throws ServiceException {
        List<User> clients = service.getAllClients();
        HttpSession session = request.getSession();
        session.setAttribute(USERS_ATTRIBUTE, clients);
    }
}