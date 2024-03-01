package com.training.utility;

import com.training.dto.EmpAttendanceResponseDTO;
import com.training.entity.EmpAttendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceObjectMapper {

    public static EmpAttendanceResponseDTO attendanceToAttendanceDTO(EmpAttendance attendance) {

        if(attendance==null)
            return null;
        EmpAttendanceResponseDTO attendanceRespDTO = new EmpAttendanceResponseDTO();
        attendanceRespDTO.setId(attendance.getId());
        attendanceRespDTO.setEmpId(attendance.getEmpId());
        attendanceRespDTO.setName(attendance.getName());
        attendanceRespDTO.setGender(attendance.getGender());
        attendanceRespDTO.setDepartment(attendance.getDepartment());
        attendanceRespDTO.setFirstSwipeIn(attendance.getFirstSwipeIn());
        attendanceRespDTO.setLastSwipeOut(attendance.getLastSwipeOut());
        attendanceRespDTO.setNoOfHours(attendance.getNoOfHours());
        attendanceRespDTO.setDate(attendance.getDate());
        return attendanceRespDTO;

    }

    public static List<EmpAttendanceResponseDTO> attendanceListToAttendanceDTO(List<EmpAttendance> listAttendance) {

        List<EmpAttendanceResponseDTO>  attendanceListDTO = new ArrayList<>();
        listAttendance.forEach(attendance -> {
            attendanceListDTO.add(attendanceToAttendanceDTO(attendance));
        });
        return attendanceListDTO;

    }

}
