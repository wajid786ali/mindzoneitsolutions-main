package com.mindzone.repository;

import com.mindzone.entity.StudentFeedBack;
import com.mindzone.entity.StudentNotes;
import com.mindzone.entity.Students;
import com.mindzone.entity.Worksheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentNotesRepository extends JpaRepository<StudentNotes, UUID> {
   List<StudentNotes> findByResolvedAndCenter(String resolved,String center);
   Optional<StudentNotes> findById(UUID studentId);
}
