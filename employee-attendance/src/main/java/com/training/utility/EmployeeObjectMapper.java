package com.training.utility;

import com.training.dto.EmployeeRequestDTO;
import com.training.dto.EmployeeResponseDTO;
import com.training.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeObjectMapper {

    public static EmployeeResponseDTO empToEmpDTO(Employee employee) {
        if(employee==null)
            return null;
        EmployeeResponseDTO empRespDTO = new EmployeeResponseDTO();
        empRespDTO.setId(employee.getId());
        empRespDTO.setName(employee.getName());
        empRespDTO.setDepartment(employee.getDepartment());
        empRespDTO.setGender(employee.getGender());

        return empRespDTO;
    }
    public static List<EmployeeResponseDTO> empListToEmpListDTO(List<Employee> empList) {

        List<EmployeeResponseDTO>  empListDTO = new ArrayList<>();
         empList.forEach(employee -> {
             empListDTO.add(empToEmpDTO(employee));
        });
        return empListDTO;
    }

    public static Employee empDTOToEmp(EmployeeRequestDTO employeeDTO) {
        if(employeeDTO==null)
            return null;
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setGender(employeeDTO.getGender());

        return employee;
    }

}
