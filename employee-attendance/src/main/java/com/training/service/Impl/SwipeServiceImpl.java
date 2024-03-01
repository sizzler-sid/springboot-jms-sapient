package com.training.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.training.entity.Employee;
import com.training.entity.Swipe;
import com.training.exception.CustomException;
import com.training.repository.SwipeRepository;
import com.training.service.SwipeService;
import com.training.utility.SwipeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SwipeServiceImpl implements SwipeService {

    private static final Logger logger = LoggerFactory.getLogger(SwipeServiceImpl.class);

    @Autowired
    private SwipeRepository swipeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Transactional
    public Swipe createSwipeInRecord(int id) {
        try {
            Swipe swipe = createSwipeRecord(id);
            swipe.setSwipeType(SwipeType.SWIPE_IN.toString());

            LocalDateTime dateTimeNow = getCurrentDateTime();
            swipe.setDateTime(dateTimeNow);
            swipe.setDate(dateTimeNow.toLocalDate());

            return swipeRepository.save(swipe);
        } catch (Exception e) {
            logger.error("An error occurred while creating swipe-in record for employee ID: {}", id, e);
            throw new CustomException("Failed to create swipe-in record.", e);
        }
    }

    @Transactional
    public Swipe createSwipeOutRecord(int id) {
        try {
            Swipe swipe = createSwipeRecord(id);
            swipe.setSwipeType(SwipeType.SWIPE_OUT.toString());

            LocalDateTime dateTimeNow = getCurrentDateTime();
            swipe.setDateTime(dateTimeNow);
            swipe.setDate(dateTimeNow.toLocalDate());

            return swipeRepository.save(swipe);
        } catch (Exception e) {
            logger.error("An error occurred while creating swipe-out record for employee ID: {}", id, e);
            throw new CustomException("Failed to create swipe-out record.", e);
        }
    }

    @Transactional
    public Swipe createCustomSwipeRecord(Swipe swipe, int id) {
        try {
            Employee emp = employeeServiceImpl.findOne(id);
            swipe.setEmp(emp);
            swipe.setDate(swipe.getDateTime().toLocalDate());
            return swipeRepository.save(swipe);
        } catch (Exception e) {
            logger.error("An error occurred while creating custom swipe record for employee ID: {}", id, e);
            throw new CustomException("Failed to create custom swipe record.", e);
        }
    }

    @Transactional
    public void deleteSwipeRecord(int id) {
        try {
            swipeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while deleting swipe record with ID: {}", id, e);
            throw new CustomException("Failed to delete swipe record with ID: " + id, e);
        }
    }

    private Swipe createSwipeRecord(int id) {
        Swipe swipe = new Swipe();
        Employee emp = employeeServiceImpl.findOne(id);
        swipe.setEmp(emp);
        return swipe;
    }

    private LocalDateTime getCurrentDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(ldt.format(dtf), dtf);
    }
}
