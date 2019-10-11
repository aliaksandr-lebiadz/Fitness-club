package com.epam.fitness.utils.data.loader;

import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface PageDataLoader {

    void loadDataToSession(HttpServletRequest request) throws ServiceException;

}