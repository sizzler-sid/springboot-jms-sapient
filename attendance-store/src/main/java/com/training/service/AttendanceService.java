package com.training.service;

import com.training.entity.EmpAttendance;
import com.training.model.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    public void saveAttendance(List<Attendance> listAttendance);
    public List<EmpAttendance> getAttendanceByDate(LocalDate date);
    public List<EmpAttendance> getAttendanceByDateRange(LocalDate fromDate, LocalDate toDate);
    public List<EmpAttendance> getAttendanceByName(String name);


}
