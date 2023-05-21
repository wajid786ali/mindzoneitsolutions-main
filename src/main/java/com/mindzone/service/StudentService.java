package com.mindzone.service;

import com.mindzone.dto.StudentFeedBackDto;
import com.mindzone.dto.StudentRequestDto;
import com.mindzone.dto.StudentResponseDto;
import com.mindzone.entity.Students;

import java.util.List;

public interface StudentService {

    StudentResponseDto create(StudentRequestDto studentRequestDto);

    StudentResponseDto update(StudentRequestDto studentRequestDto);

    StudentResponseDto get(long userId);

    List<StudentResponseDto> getAll();

    public List<StudentFeedBackDto> getFeedBacks();

    public String addFeedBack(String studentID,String sName,String noOfWorksheet,String comments,String worksheetType,String teacherName);
    void delete(long userId);

}
