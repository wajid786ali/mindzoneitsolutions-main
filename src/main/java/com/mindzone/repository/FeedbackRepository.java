package com.mindzone.repository;

import com.mindzone.entity.StudentFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<StudentFeedBack, Integer> {

}
