package com.epam.fitness.command.factory.impl;

import com.epam.fitness.command.factory.CommandFactoryHelper;
import com.epam.fitness.dao.factory.DaoFactory;
import com.epam.fitness.service.impl.GymMembershipServiceImpl;
import com.epam.fitness.service.impl.UserServiceImpl;
import com.epam.fitness.utils.data.loader.PageDataLoader;
import com.epam.fitness.utils.data.loader.impl.HomePageDataLoader;
import com.epam.fitness.utils.data.loader.impl.OrderPageDataLoader;
import com.epam.fitness.utils.data.loader.impl.UsersPageDataLoader;

public class CommandFactoryHelperImpl implements CommandFactoryHelper {

    private static final String HOME_PAGE_URL = "/home";
    private static final String USERS_PAGE_URL = "/users";
    private static final String ORDER_PAGE_URL = "/getMembership";

    private DaoFactory factory;

    public CommandFactoryHelperImpl(DaoFactory factory){
        this.factory = factory;
    }

    @Override
    public PageDataLoader getPageDataLoader(String pageUrl) {
        PageDataLoader loader;
        switch (pageUrl){
            case HOME_PAGE_URL:
                loader = new HomePageDataLoader();
                break;
            case USERS_PAGE_URL:
                loader = new UsersPageDataLoader(
                        new UserServiceImpl(factory)
                );
                break;
            case ORDER_PAGE_URL:
                loader = new OrderPageDataLoader(
                        new GymMembershipServiceImpl(factory)
                );
                break;
            default:
                throw new IllegalArgumentException("Invalid page url: " + pageUrl);
        }
        return loader;
    }
}