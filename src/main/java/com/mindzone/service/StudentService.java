package com.mindzone.service;

import com.mindzone.dto.*;
import com.mindzone.entity.StudentNotes;
import com.mindzone.entity.Students;
import com.mindzone.entity.UserName;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface StudentService {

    StudentResponseDto create(StudentRequestDto studentRequestDto);

    StudentResponseDto update(StudentRequestDto studentRequestDto);

    StudentResponseDto get(long userId);

    List<StudentResponseDto> getAll();

    public List<StudentFeedBackDto> getFeedBacks();

    public String addFeedBack(String studentID, String sName, String noOfWorksheet, String comments, String worksheetType, String teacherName);

    void delete(long userId);

    String addWorksheet(WorksheetsDto sdto);
    String addWeeklyWorksheet(List<WorksheetsDto> weeklyWSList);

    List<WorksheetsDto> getListWorksheet();

    public List<WorksheetsDto> getStudentWorksheet(long studentId);

    public List<WorksheetsDto> getStudentWorksheet(String month);

    public List<WorksheetsDto> findByInsertDate(Date insertDate);

    String addTeachers(TeachersDto sdto);

    String addStudentNotes(StudentNotesDto sdto);

    List<TeachersDto> getListTeachers();

    public List<WorksheetsDto> getListNextWeekWorksheet();

    List<StudentNotesDto> getStudentNotes(String status);

    public UserNameDto userName(String email, String password);

    void reminderDelete(String studentId);

    void teacherDelete(String email);
}
