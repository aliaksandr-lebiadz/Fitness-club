package com.epam.fitness.command.impl.order;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.exception.ValidationException;
import com.epam.fitness.service.api.OrderService;
import com.epam.fitness.validator.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendFeedbackCommand implements Command {

    private static final String ORDER_ID_PARAMETER = "order_id";
    private static final String FEEDBACK_PARAMETER = "feedback";
    private static final String ORDERS_PAGE = "/orders";

    private OrderService service;
    private OrderValidator validator;

    public SendFeedbackCommand(OrderService service, OrderValidator validator){
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ValidationException {
        String feedback = request.getParameter(FEEDBACK_PARAMETER);
        if(!validator.isFeedbackValid(feedback)){
            throw new ValidationException();
        }
        String orderIdStr = request.getParameter(ORDER_ID_PARAMETER);
        int orderId = Integer.parseInt(orderIdStr);
        service.updateFeedbackById(orderId, feedback);
        return CommandResult.redirect(ORDERS_PAGE);
    }
}