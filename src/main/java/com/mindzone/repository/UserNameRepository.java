package com.mindzone.repository;

import com.mindzone.entity.StudentNotes;
import com.mindzone.entity.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserNameRepository extends JpaRepository<UserName, UUID> {
   UserName findByEmail(String email);
}
