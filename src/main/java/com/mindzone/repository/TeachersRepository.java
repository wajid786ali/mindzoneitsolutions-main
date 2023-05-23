package com.mindzone.repository;

import com.mindzone.entity.StudentFeedBack;
import com.mindzone.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer> {

}

