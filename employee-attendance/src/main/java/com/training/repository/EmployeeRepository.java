package com.training.repository;

import com.training.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e Inner Join e.swipe s where s.date = :date")
    List<Employee> findAllEmpByDate(@Param("date") LocalDate date);

}
