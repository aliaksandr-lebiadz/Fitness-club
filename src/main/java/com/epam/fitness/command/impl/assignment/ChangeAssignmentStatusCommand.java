package com.epam.fitness.command.impl.assignment;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.entity.assignment.Assignment;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.api.AssignmentService;
import com.epam.fitness.entity.assignment.AssignmentStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeAssignmentStatusCommand implements Command {

    private static final String ASSIGNMENTS_PAGE = "/controller?command=showAssignments&order_id=%d";
    private static final String ASSIGNMENT_ID_PARAMETER = "assignment_id";
    private static final String ASSIGNMENT_ACTION_PARAMETER = "assignment_action";
    private static final String ACCEPT_ACTION = "accept";
    private static final String CANCEL_ACTION = "cancel";

    private AssignmentService service;

    public ChangeAssignmentStatusCommand(AssignmentService service){
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String assignmentIdStr = request.getParameter(ASSIGNMENT_ID_PARAMETER);
        int assignmentId = Integer.parseInt(assignmentIdStr);
        Optional<Assignment> assignmentOptional = service.findById(assignmentId);
        if(assignmentOptional.isPresent()){
            Assignment assignment = assignmentOptional.get();
            int orderId = assignment.getOrderId();
            String page = insertParameterIntoAssignmentsPage(orderId);
            String assignmentAction = request.getParameter(ASSIGNMENT_ACTION_PARAMETER);
            AssignmentStatus status = getStatus(assignmentAction);
            service.changeStatusById(assignmentId, status);
            return CommandResult.forward(page);
        } else{
            throw new ServiceException("Invalid assignment id: " + assignmentId);
        }
    }

    private AssignmentStatus getStatus(String assignmentAction){
        AssignmentStatus status;
        switch (assignmentAction){
            case ACCEPT_ACTION:
                status = AssignmentStatus.ACCEPTED;
                break;
            case CANCEL_ACTION:
                status = AssignmentStatus.CANCELED;
                break;
            default:
                status = null;
        }
        return status;
    }

    private String insertParameterIntoAssignmentsPage(int orderId){
        return String.format(ASSIGNMENTS_PAGE, orderId);
    }
}