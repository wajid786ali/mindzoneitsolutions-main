package com.mindzone.repository;

import com.mindzone.entity.StudentNotes;
import com.mindzone.entity.Students;
import com.mindzone.entity.Worksheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorksheetsRepository extends JpaRepository<Worksheets, Integer> {
    List<Worksheets> findByStudentId(Long studentId);
    List<Worksheets> findByMonth(String month);
    Optional<Worksheets> findById(UUID id);
    List<Worksheets> findByWeekDate(Date weekDate);

    List<Worksheets> findByWeekDateGreaterThan(Date weekDate);
    @Query(value = "select * from work_sheets t inner join (select student_name , max(week_date ) as MaxDate from work_sheets group by student_name ) tm on t.student_name  = tm.student_name  and t.week_date  = tm.MaxDate", nativeQuery = true)
    List<Worksheets> findLastWeekyWorksheet();

}


