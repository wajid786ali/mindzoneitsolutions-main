package com.mindzone.repository;

import com.mindzone.entity.Students;
import com.mindzone.entity.Worksheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorksheetsRepository extends JpaRepository<Worksheets, Integer> {
    List<Worksheets> findByStudentId(Long studentId);
    List<Worksheets> findByMonth(String month);
    }


