package com.training.utility;

import com.training.entity.Employee;
import com.training.entity.Swipe;
import com.training.model.Attendance;

public class AttendanceObjectMapper {

    public static Attendance convertToAttendanceObject(Employee emp, Swipe swipeIn, Swipe swipeOut, String attendanceStatus, long hoursPresent) {
        Attendance attendance = new Attendance();
        attendance.setEmpId(emp.getId());
        attendance.setGender(emp.getGender());
        attendance.setName(emp.getName());
        attendance.setDate(swipeIn.getDateTime().toLocalDate());
        attendance.setFirstSwipeIn(swipeIn.getDateTime());
        attendance.setLastSwipeOut(swipeOut.getDateTime());
        attendance.setNoOfHours(hoursPresent);
        attendance.setAttendanceStatus(attendanceStatus);
        return attendance;
    }

}
