package com.training.repository;

import com.training.entity.EmpAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<EmpAttendance, String> {

    @Query("{'date': {$gte:?0, $lte:?1}}")
    List<EmpAttendance> findByDate(LocalDate date, LocalDate date1);

    List<EmpAttendance> findByName(String name);

}
