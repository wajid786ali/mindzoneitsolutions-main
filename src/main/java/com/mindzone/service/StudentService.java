package com.mindzone.service;

import com.mindzone.dto.*;
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

    String addWorksheet(WorksheetsDto sdto);

    List<WorksheetsDto> getListWorksheet();

    public List<WorksheetsDto> getStudentWorksheet(long studentId);
    public List<WorksheetsDto> getStudentWorksheet(String month);

    String addTeachers(TeachersDto sdto);

    List<TeachersDto> getListTeachers();
}
