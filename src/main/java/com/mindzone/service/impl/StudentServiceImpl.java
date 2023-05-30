package com.mindzone.service.impl;

import com.mindzone.dto.*;
import com.mindzone.entity.*;
import com.mindzone.exception.UserNotFoundException;
import com.mindzone.mapper.*;
import com.mindzone.repository.*;
import com.mindzone.service.StudentService;
import com.mindzone.utils.MZUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private StudentNotesRepository studentNotesRepository;
    @Autowired
    private TeachersRepository teachersRepository;
    @Autowired
    private TeachersMapper teachersMapper;
    @Autowired
    private StudentNotesMapper studentNotesMapper;
    @Autowired
    private WorksheetsRepository worksheetsRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentFeedBackMapper studentFeedBackMapper;

    @Autowired
    private WorksheetsMapper worksheetsMapper;

    @Autowired
    private MZUtils mzUtils;


    @Override
    public StudentResponseDto create(StudentRequestDto studentRequestDto) {

        Students student = studentMapper.toEntity(studentRequestDto);
        //Change generate id logic as required.
        long studentId = mzUtils.generateId();
        student.setStudentId(studentId);

        Students savedStudent = repository.save(student);
        return studentMapper.toDto(savedStudent);
    }



    @Override
    public StudentResponseDto update(StudentRequestDto studentRequestDto) {

        Students student = studentMapper.toEntity(studentRequestDto);
        Students savedUser = null;
        try {
            Students foundStudent = repository.findByStudentId(studentRequestDto.getStudentId()).orElseThrow(() -> new UserNotFoundException("Student not found by studentId:" + studentRequestDto.getStudentId()));
            if (nonNull(foundStudent)) {
                foundStudent.setStudentName(student.getStudentName());
                foundStudent.setParentsName(student.getParentsName());
                foundStudent.setEmail(student.getEmail());
                foundStudent.setGrade(student.getGrade());
                foundStudent.setAddress(student.getAddress());
                foundStudent.setPhoneNumber(student.getPhoneNumber());
                foundStudent.setStartDate(student.getStartDate());
                foundStudent.setEndDate(student.getEndDate());
                foundStudent.setStatus(student.getStatus());
                foundStudent.setSubjects(student.getSubjects());
                foundStudent.setDays(student.getDays());
                savedUser = repository.save(foundStudent);
            }
        } catch (Exception ex) {
            log.error("Exception occurred in update method..!!"+ex.getMessage());
        }
        return studentMapper.toDto(savedUser);
    }

    @Override
    public void delete(long studentId) {
        try {
            Students user = repository.findByStudentId(studentId).orElseThrow(() -> new UserNotFoundException("User not found by studentId:" + studentId));
            repository.delete(user);
        }catch(UserNotFoundException ex){
            log.error("Exception in delete method..!!",ex.getMessage());
        }
    }

    @Override
    public StudentResponseDto get(long studentId) {
        Students user = repository.findByStudentId(studentId).orElseThrow(() -> new UserNotFoundException("User not found by studentId:" + studentId));
        return studentMapper.toDto(user);
    }



    @Override
    public List<StudentResponseDto> getAll() {
        List<Students> students = repository.findAll(Sort.by(Sort.Direction.ASC,"studentId"));
        return students.stream().map(student -> studentMapper.toDto(student)).collect(Collectors.toList());
    }

    @Override
    public List<StudentFeedBackDto> getFeedBacks() {
        List<StudentFeedBack> studentFeedBacks = feedbackRepository.findAll(Sort.by(Sort.Direction.ASC,"studentId"));
        return studentFeedBacks.stream().map(ws -> studentFeedBackMapper.toDto(ws)).collect(Collectors.toList());
    }

    @Override
    public List<WorksheetsDto> getListWorksheet(){
    //    List<Worksheets> studentWorksheets= worksheetsRepository.findAll(Sort.by(Sort.Direction.ASC,"studentId"));
        List<Worksheets> studentWorksheets= worksheetsRepository.findByWorksheetWithDate("aaa");
        List<WorksheetsDto>  woksheet=studentWorksheets.stream().map(wsm -> worksheetsMapper.toDto(wsm)).collect(Collectors.toList());
        return woksheet;
    }

    @Override
    public List<WorksheetsDto> getListNextWeekWorksheet(){
        List<Worksheets> studentWorksheets= worksheetsRepository.findAll(Sort.by(Sort.Direction.ASC,"studentId"));
        List<WorksheetsDto>  woksheet=studentWorksheets.stream().map(wsm -> worksheetsMapper.toDto(wsm)).collect(Collectors.toList());

        return woksheet;
    }

    @Override
    public List<WorksheetsDto> getStudentWorksheet(long studentId){
        List<Worksheets> studentWorksheets= worksheetsRepository.findByStudentId(studentId);
        return studentWorksheets.stream().map(wsm -> worksheetsMapper.toDto(wsm)).collect(Collectors.toList());

    }

    @Override
    public List<WorksheetsDto> getStudentWorksheet(String month){
        List<Worksheets> studentWorksheets= worksheetsRepository.findByMonth(month);
        return studentWorksheets.stream().map(wsm -> worksheetsMapper.toDto(wsm)).collect(Collectors.toList());

    }

    @Override
    public List<TeachersDto> getListTeachers() {
        List<Teachers> teachersList= teachersRepository.findAll(Sort.by(Sort.Direction.ASC,"teacherName"));
        return teachersList.stream().map(tr -> teachersMapper.toDto(tr)).collect(Collectors.toList());

    }

    @Override
    public List<StudentNotesDto> getStudentNotes() {
        List<StudentNotes> studentNotes= studentNotesRepository.findByResolved("N");
        return studentNotes.stream().map(wsm -> studentNotesMapper.toDto(wsm)).collect(Collectors.toList());
    }

    @Override
    public String addTeachers(TeachersDto sdto) {
        try {
            Teachers teachers = new Teachers();
            teachers.setActive(true);
            teachers.setTeacherName(sdto.getTeacherName());
            teachers.setEmail(sdto.getEmail());
            teachers.setPhoneNumber(sdto.getPhoneNumber());
            teachers.setAddress(sdto.getAddress());
            teachers.setStartDate(sdto.getStartDate());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            teachers.setInsrtDate(date);
            teachersRepository.save(teachers);
        } catch (Exception e){
            return e.toString();
        }
        return "success";
    }



    @Override
    public String addWorksheet(WorksheetsDto sdto) {
        try {
            Worksheets worksheets = new Worksheets();
            worksheets.setStudentId(sdto.getStudentId());
            worksheets.setStudentName(sdto.getStudentName());
            worksheets.setGrade(sdto.getGrade());
            worksheets.setWeekDate(sdto.getWeekDate());
            worksheets.setWorksheet(sdto.getWorksheet());
            worksheets.setExtraWorksheet(sdto.getExtraWorksheet());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            worksheets.setInsertDate(date);
            worksheetsRepository.save(worksheets);
        } catch (Exception e){
            return e.toString();
        }
        return "success";
    }


    @Override
    public String addStudentNotes(StudentNotesDto sdto){
        try {
            StudentNotes studentNotes = new StudentNotes();
            studentNotes.setStudentName(sdto.getStudentName());
            studentNotes.setComments(sdto.getComments());
            studentNotes.setStudentId(sdto.getStudentId());
            studentNotes.setResolved("N");
            studentNotes.setReminderDate(sdto.getReminderDate());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            studentNotes.setCreatedDate(date);
            studentNotesRepository.save(studentNotes);
        } catch (Exception e){
            return e.toString();
        }
        return "success";
    }
    @Override
    public String addFeedBack(String studentID,String sName,String noOfWorksheet,String comments,String worksheetType,String teacherName){
        try {
            StudentFeedBack studentFeedBack = new StudentFeedBack();
            //  studentFeedBack.setStudentId(Long.parseLong(studentID));
            studentFeedBack.setStudentName(sName);
            studentFeedBack.setComments(comments);
            studentFeedBack.setNoOfWorksheets(Integer.parseInt(noOfWorksheet));
            studentFeedBack.setTeacherName(teacherName);
            studentFeedBack.setWorksheetsType(worksheetType);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            studentFeedBack.setInsertDate(date);
            feedbackRepository.save(studentFeedBack);
        } catch (Exception e){
            return e.toString();
        }
        return "success";
    }
}
