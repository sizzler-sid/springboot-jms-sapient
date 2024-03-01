package com.training.service;

import com.training.entity.EmpAttendance;
import com.training.exception.CustomException;
import com.training.model.Attendance;
import com.training.repository.AttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void saveAttendance(List<Attendance> listAttendance) {
        try {
            listAttendance.forEach(a -> attendanceRepository.save(convertToEmpAttendanceObject(a)));
        } catch (Exception e) {
            logger.error("An error occurred while saving attendance", e);
            throw new CustomException("Failed to create attendance.", e);
        }
    }

    private EmpAttendance convertToEmpAttendanceObject(Attendance attendance) {
        EmpAttendance empAttendance = new EmpAttendance();
        empAttendance.setEmpId(attendance.getEmpId());
        empAttendance.setGender(attendance.getGender());
        empAttendance.setName(attendance.getName());
        empAttendance.setDepartment(attendance.getDepartment());
        empAttendance.setDate(attendance.getDate());
        empAttendance.setFirstSwipeIn(attendance.getFirstSwipeIn().toString());
        empAttendance.setLastSwipeOut(attendance.getLastSwipeOut().toString());
        empAttendance.setNoOfHours(attendance.getNoOfHours());
        empAttendance.setAttendanceStatus(attendance.getAttendanceStatus());
        return empAttendance;
    }

    public List<EmpAttendance> getAttendanceByDate(LocalDate date) {
        try {
            logger.info("Fetching attendance by date: {}", date);
            return attendanceRepository.findByDate(date, date);
        } catch (Exception e) {
            logger.error("An error occurred while fetching attendance by date: {}", date, e);
            throw new CustomException("Failed to fetch attendance by date.", e);
        }
    }

    public List<EmpAttendance> getAttendanceByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            logger.info("Fetching attendance by date range: {} to {}", fromDate, toDate);
            return attendanceRepository.findByDate(fromDate, toDate);
        } catch (Exception e) {
            logger.error("An error occurred while fetching attendance by date range: {} to {}", fromDate, toDate, e);
            throw new CustomException("Failed to fetch attendance by date range.", e);
        }
    }

    public List<EmpAttendance> getAttendanceByName(String name) {
        try {
            logger.info("Fetching attendance by name: {}", name);
            return attendanceRepository.findByName(name);
        } catch (Exception e) {
            logger.error("An error occurred while fetching attendance by name: {}", name, e);
            throw new CustomException("Failed to fetch attendance by name.", e);
        }
    }

}
