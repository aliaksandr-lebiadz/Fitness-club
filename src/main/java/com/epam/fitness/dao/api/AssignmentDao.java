package com.epam.fitness.dao.api;

import com.epam.fitness.exception.DaoException;
import com.epam.fitness.entity.assignment.Assignment;

import java.util.List;
import java.util.Optional;

/**
 * <p>An interface specified for an assignment
 * entity to provide an access to it.</p>
 */
public interface AssignmentDao extends Dao<Assignment>{

    /**
     * <p>Gets all assignments by the supplied order's id.</p>
     *
     * @param orderId order's id
     * @return list of found assignments
     */
    List<Assignment> getAllByOrderId(int orderId) throws DaoException;

}