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

    List<StudentResponseDto> getAll(String center);

    public List<StudentFeedBackDto> getFeedBacks();

    public String addFeedBack(String studentID, String sName, String noOfWorksheet, String comments, String worksheetType, String teacherName);

    void delete(long userId);

    String addWorksheet(WorksheetsDto sdto);
    String addWeeklyWorksheet(List<WorksheetsDto> weeklyWSList);
    public String updateWeeklyWorksheet(WorksheetsDto worksheetsDto);
    List<WorksheetsDto> getListWorksheet();

    public List<WorksheetsDto> getStudentWorksheet(long studentId);

    public List<WorksheetsDto> getStudentWorksheet(String month);

    public List<WorksheetsDto> getStudentWorksheetByStatus(String status);

    public List<WorksheetsDto> findByInsertDate(Date insertDate);

    public List<WorksheetsDto>  findLastWeekyWorksheet();

    String addTeachers(TeachersDto sdto);

    String addStudentNotes(StudentNotesDto sdto);

    List<TeachersDto> getListTeachers();

    public List<WorksheetsDto> getListNextWeekWorksheet();

    List<StudentNotesDto> getStudentNotes(String status,String center);

    public TeachersDto userName(String email, String password);

    void reminderDelete(String studentId);

    String teacherDelete(String email);
}
