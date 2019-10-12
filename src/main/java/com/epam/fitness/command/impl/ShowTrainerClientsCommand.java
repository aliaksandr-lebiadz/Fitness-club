package com.epam.fitness.command.impl;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.entity.assignment.Exercise;
import com.epam.fitness.entity.user.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.ExerciseService;
import com.epam.fitness.service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTrainerClientsCommand implements Command {

    private static final String TRAINER_CLIENTS_PAGE = "/trainerClients";
    private static final String USER_ATTRIBUTE = "user";
    private static final String CLIENTS_ATTRIBUTE = "clients";
    private static final String EXERCISES_ATTRIBUTE = "exercises";

    private UserService userService;
    private ExerciseService exerciseService;

    public ShowTrainerClientsCommand(UserService userService, ExerciseService exerciseService){
        this.userService = userService;
        this.exerciseService = exerciseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User trainer = (User)session.getAttribute(USER_ATTRIBUTE);
        int trainerId = trainer.getId();
        List<User> clients = userService.findUsersByTrainerId(trainerId);
        session.setAttribute(CLIENTS_ATTRIBUTE, clients);
        List<Exercise> exercises = exerciseService.getAll();
        session.setAttribute(EXERCISES_ATTRIBUTE, exercises);
        return CommandResult.redirect(TRAINER_CLIENTS_PAGE);
    }
}