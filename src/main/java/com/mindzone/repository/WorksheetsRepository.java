package com.mindzone.repository;

import com.mindzone.entity.Students;
import com.mindzone.entity.Worksheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorksheetsRepository extends JpaRepository<Worksheets, Integer> {
    List<Worksheets> findByStudentId(Long studentId);
    List<Worksheets> findByMonth(String month);

    List<Worksheets> findByWeekDate(Date weekDate);
    @Query("SELECT a FROM  Worksheets a WHERE insertDate  > '2023-05-21'" )
    List<Worksheets> findByWorksheetWithDate(String insertDate);

}


