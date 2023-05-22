package com.mindzone.repository;

import com.mindzone.entity.Worksheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksheetsRepository extends JpaRepository<Worksheets, Integer> {

    }


