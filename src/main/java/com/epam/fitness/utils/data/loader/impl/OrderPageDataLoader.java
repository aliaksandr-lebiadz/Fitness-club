package com.epam.fitness.utils.data.loader.impl;

import com.epam.fitness.entity.GymMembership;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.GymMembershipService;
import com.epam.fitness.utils.data.loader.PageDataLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderPageDataLoader implements PageDataLoader {

    private static final String MEMBERSHIPS_ATTRIBUTE = "memberships";

    private GymMembershipService service;

    public OrderPageDataLoader(GymMembershipService service){
        this.service = service;
    }

    @Override
    public void loadDataToSession(HttpServletRequest request) throws ServiceException {
        List<GymMembership> memberships = service.getAll();
        HttpSession session = request.getSession();
        session.setAttribute(MEMBERSHIPS_ATTRIBUTE, memberships);
    }
}