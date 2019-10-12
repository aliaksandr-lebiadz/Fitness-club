package com.epam.fitness.command.impl;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.entity.Order;
import com.epam.fitness.entity.user.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTrainerClientOrdersCommand implements Command {

    private static final String TRAINER_CLIENTS_PAGE = "/trainerClients";
    private static final String CLIENT_ID_PARAMETER = "client_id";
    private static final String CLIENT_ORDERS_ATTRIBUTE = "client_orders";
    private static final String USER_ATTRIBUTE = "user";

    private OrderService service;

    public ShowTrainerClientOrdersCommand(OrderService service){
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String clientIdStr = request.getParameter(CLIENT_ID_PARAMETER);
        int clientId = Integer.parseInt(clientIdStr);
        HttpSession session = request.getSession();
        User trainer = (User) session.getAttribute(USER_ATTRIBUTE);
        int trainerId = trainer.getId();
        List<Order> clientOrders = service.getClientOrdersWithTrainerId(clientId, trainerId);
        session.setAttribute(CLIENT_ORDERS_ATTRIBUTE, clientOrders);
        return CommandResult.redirect(TRAINER_CLIENTS_PAGE);
    }
}