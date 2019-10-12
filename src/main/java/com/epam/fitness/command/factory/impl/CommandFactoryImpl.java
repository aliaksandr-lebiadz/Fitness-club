package com.epam.fitness.command.factory.impl;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.factory.CommandFactory;
import com.epam.fitness.command.factory.CommandFactoryHelper;
import com.epam.fitness.command.impl.*;
import com.epam.fitness.command.impl.assignment.ChangeAssignmentCommand;
import com.epam.fitness.command.impl.order.SendFeedbackCommand;
import com.epam.fitness.command.impl.order.ShowOrdersCommand;
import com.epam.fitness.command.impl.user.LogOutCommand;
import com.epam.fitness.command.impl.user.LoginCommand;
import com.epam.fitness.dao.factory.DaoFactory;
import com.epam.fitness.service.api.AssignmentService;
import com.epam.fitness.service.api.ExerciseService;
import com.epam.fitness.service.api.OrderService;
import com.epam.fitness.service.api.UserService;
import com.epam.fitness.service.impl.*;
import com.epam.fitness.command.impl.assignment.ChangeAssignmentStatusCommand;
import com.epam.fitness.command.impl.assignment.ShowAssignmentsCommand;
import com.epam.fitness.command.impl.order.AssignNutritionTypeCommand;
import com.epam.fitness.command.impl.user.SetUserDiscountCommand;
import com.epam.fitness.utils.CurrentPageGetter;
import com.epam.fitness.utils.OrderUtils;
import com.epam.fitness.validator.AssignmentValidator;
import com.epam.fitness.validator.OrderValidator;
import com.epam.fitness.validator.PaymentValidator;
import com.epam.fitness.validator.UserValidator;

public class CommandFactoryImpl implements CommandFactory {

    private static final String HOME_PAGE_URL = "/home";
    private static final String USERS_PAGE_URL = "/users";
    private static final String ORDER_PAGE_URL = "/getMembership";
    private static final String LOGIN_COMMAND = "login";
    private static final String LOG_OUT_COMMAND = "logOut";
    private static final String SHOW_HOME_PAGE_COMMAND = "showHomePage";
    private static final String SET_LOCALE_COMMAND = "setLocale";

    public static final String SHOW_ORDER_PAGE_COMMAND = "showOrderPage";
    public static final String GET_MEMBERSHIP_COMMAND = "getMembership";
    public static final String SHOW_ORDERS_COMMAND = "showOrders";
    public static final String SEND_FEEDBACK_COMMAND = "sendFeedback";
    public static final String SHOW_TRAINER_CLIENTS_COMMAND = "showTrainerClients";
    public static final String SHOW_TRAINER_CLIENT_ORDERS_COMMAND = "showTrainerClientOrders";
    public static final String ASSIGN_NUTRITION_TYPE_COMMAND = "assignNutritionType";
    public static final String SHOW_ASSIGNMENTS_COMMAND = "showAssignments";
    public static final String CHANGE_ASSIGNMENT_STATUS_COMMAND = "changeAssignmentStatus";
    public static final String CHANGE_ASSIGNMENT_COMMAND = "changeAssignment";
    public static final String SHOW_USERS_PAGE_COMMAND = "showUsersPage";
    public static final String SET_USER_DISCOUNT_COMMAND = "setUserDiscount";

    private DaoFactory factory;
    private CommandFactoryHelper helper;

    public CommandFactoryImpl(DaoFactory factory, CommandFactoryHelper helper){
        this.factory = factory;
        this.helper = helper;
    }


    public Command create(String commandValue){
        Command command;
        switch (commandValue){
            case LOGIN_COMMAND:
                command =  new LoginCommand(
                        getUserService()
                );
                break;
            case SHOW_ORDER_PAGE_COMMAND:
                command = new ShowPageCommand(
                        ORDER_PAGE_URL,
                        helper.getPageDataLoader(ORDER_PAGE_URL)
                );
                break;
            case GET_MEMBERSHIP_COMMAND:
                command =  new GetMembershipCommand(
                        getOrderService(),
                        new PaymentValidator()
                );
                break;
            case SHOW_ORDERS_COMMAND:
                command =  new ShowOrdersCommand(
                       getOrderService()
                );
                break;
            case SEND_FEEDBACK_COMMAND:
                command =  new SendFeedbackCommand(
                        getOrderService(),
                        new OrderValidator()
                );
                break;
            case SHOW_TRAINER_CLIENTS_COMMAND:
                command = new ShowTrainerClientsCommand(
                        getUserService(),
                        getExerciseService()
                );
                break;
            case SHOW_TRAINER_CLIENT_ORDERS_COMMAND:
                command = new ShowTrainerClientOrdersCommand(
                        getOrderService()
                );
                break;
            case ASSIGN_NUTRITION_TYPE_COMMAND:
                command = new AssignNutritionTypeCommand(
                        getOrderService()
                );
                break;
            case SHOW_ASSIGNMENTS_COMMAND:
                command = new ShowAssignmentsCommand(
                        getAssignmentService(),
                        getExerciseService()
                );
                break;
            case CHANGE_ASSIGNMENT_STATUS_COMMAND:
                command = new ChangeAssignmentStatusCommand(
                        getAssignmentService()
                );
                break;
            case CHANGE_ASSIGNMENT_COMMAND:
                command = new ChangeAssignmentCommand(
                        getAssignmentService(),
                        new AssignmentValidator()
                );
                break;
            case SHOW_USERS_PAGE_COMMAND:
                command = new ShowPageCommand(
                        USERS_PAGE_URL,
                        helper.getPageDataLoader(USERS_PAGE_URL)
                );
                break;
            case SHOW_HOME_PAGE_COMMAND:
                command = new ShowPageCommand(
                        HOME_PAGE_URL,
                        helper.getPageDataLoader(HOME_PAGE_URL)
                );
                break;
            case SET_USER_DISCOUNT_COMMAND:
                command = new SetUserDiscountCommand(
                        getUserService(),
                        new UserValidator()
                );
                break;
            case LOG_OUT_COMMAND:
                command =  new LogOutCommand();
                break;
            case SET_LOCALE_COMMAND:
                command = new SetLocaleCommand();
                break;
            default:
                throw new IllegalArgumentException("Illegal command: " + commandValue);
        }
        return command;
    }

    private UserService getUserService(){
        return new UserServiceImpl(factory);
    }

    private OrderService getOrderService(){
        OrderUtils orderUtils = new OrderUtils();
        return new OrderServiceImpl(factory, orderUtils);
    }

    private ExerciseService getExerciseService(){
        return new ExerciseServiceImpl(factory);
    }

    private AssignmentService getAssignmentService(){
        return new AssignmentServiceImpl(factory);
    }

}
