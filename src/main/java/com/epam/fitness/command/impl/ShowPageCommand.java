package com.epam.fitness.command.impl;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.utils.data.loader.PageDataLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand implements Command {

    private String page;
    private PageDataLoader loader;

    public ShowPageCommand(String page, PageDataLoader loader){
        this.page = page;
        this.loader = loader;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        loader.loadDataToSession(request);
        return CommandResult.redirect(page);
    }
}