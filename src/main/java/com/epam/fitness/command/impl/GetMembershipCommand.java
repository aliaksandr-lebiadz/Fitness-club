package com.epam.fitness.command.impl;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.entity.user.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.exception.ValidationException;
import com.epam.fitness.service.api.OrderService;
import com.epam.fitness.validator.PaymentValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetMembershipCommand implements Command {

    private static final String ORDERS_PAGE_REQUEST = "/controller?command=showOrders";
    private static final String USER_ATTRIBUTE = "user";
    private static final String MEMBERSHIP_SELECT_PARAMETER = "membership_select";
    private static final String CARD_NUMBER_PARAMETER = "card_number";
    private static final String VALID_THRU_PARAMETER = "valid_thru";
    private static final String CVV_PARAMETER = "cvv";

    private OrderService orderService;
    private PaymentValidator paymentValidator;

    public GetMembershipCommand(OrderService orderService, PaymentValidator paymentValidator){
        this.orderService = orderService;
        this.paymentValidator = paymentValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ValidationException {
        String cardNumber = request.getParameter(CARD_NUMBER_PARAMETER);
        String validThru = request.getParameter(VALID_THRU_PARAMETER);
        String cvv = request.getParameter(CVV_PARAMETER);
        if(!isParametersValid(cardNumber, validThru, cvv)){
            throw new ValidationException();
        }

        //there should by payment process
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(USER_ATTRIBUTE);
        String membershipIdStr = request.getParameter(MEMBERSHIP_SELECT_PARAMETER);
        int membershipId = Integer.parseInt(membershipIdStr);
        orderService.create(user, membershipId);
        return CommandResult.forward(ORDERS_PAGE_REQUEST);
    }

    private boolean isParametersValid(String cardNumber, String validThru, String cvv){
        return paymentValidator.isCardNumberValid(cardNumber)
                && paymentValidator.isThruValid(validThru)
                && paymentValidator.isCvvValid(cvv);
    }
}