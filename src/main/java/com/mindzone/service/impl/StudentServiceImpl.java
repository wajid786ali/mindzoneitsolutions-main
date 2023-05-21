package com.mindzone.service.impl;

import com.mindzone.dto.StudentFeedBackDto;
import com.mindzone.dto.StudentRequestDto;
import com.mindzone.dto.StudentResponseDto;
import com.mindzone.entity.Students;
import com.mindzone.entity.StudentFeedBack;
import com.mindzone.exception.UserNotFoundException;
import com.mindzone.mapper.StudentFeedBackMapper;
import com.mindzone.mapper.StudentMapper;
import com.mindzone.repository.FeedbackRepository;
import com.mindzone.repository.StudentRepository;
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
    private StudentMapper studentMapper;

    @Autowired
    private StudentFeedBackMapper studentFeedBackMapper;

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
