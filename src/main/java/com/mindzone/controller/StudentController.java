package com.mindzone.controller;

import com.mindzone.dto.StudentRequestDto;
import com.mindzone.dto.StudentResponseDto;
import com.mindzone.dto.StudentFeedBackDto;
import com.mindzone.dto.WorksheetsDto;
import com.mindzone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mindzone.utils.MZConstants.STUDENTS;
import static com.mindzone.utils.MZConstants.URI;

@RestController
@RequestMapping(path = URI+ STUDENTS)
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;


    @PostMapping
    public @ResponseBody StudentResponseDto create(@RequestBody StudentRequestDto studentRequestDto){
    return service.create(studentRequestDto);
    }

    @RequestMapping(value = "/addFeedBack", method = RequestMethod.POST)
    public @ResponseBody String  addFeedBack( @RequestBody StudentFeedBackDto sdto){
       return service.addFeedBack(sdto.getStudentId(), sdto.getStudentName(), sdto.getNoOfWorksheets(), sdto.getComments(), sdto.getWorksheetsType(),sdto.getTeacherName());
    }

    @RequestMapping(value = "/addWorksheet", method = RequestMethod.POST)
    public @ResponseBody String  addWorksheet( @RequestBody WorksheetsDto sdto){
        return service.addWorksheet(sdto);
    }

    @GetMapping(path ="/listFeedBack")
    public @ResponseBody List<StudentFeedBackDto> getFeedback(){
        return service.getFeedBacks();
    }

    @GetMapping(path ="/listWorksheet")
    public @ResponseBody List<WorksheetsDto> listWorksheet(){
        return service.getListWorksheet();
    }

    @GetMapping(path ="/listWorksheet/{studentId}")
    public @ResponseBody List<WorksheetsDto> listStudentWorksheet(@PathVariable long studentId)
        {
            return service.getStudentWorksheet(studentId);
        }


    @GetMapping(path ="/listWorksheetbyMonth/{month}")
    public @ResponseBody List<WorksheetsDto> listStudentWorksheet(@PathVariable String month)
        {
            return service.getStudentWorksheet(month);
        }


    @PutMapping
    public @ResponseBody StudentResponseDto update(@RequestBody StudentRequestDto studentRequestDto){
        return service.update(studentRequestDto);
    }

    @GetMapping(path ="/{studentId}")
    public @ResponseBody StudentResponseDto get(@PathVariable long studentId){
        return service.get(studentId);
    }
    @GetMapping(path ="/all")
    public @ResponseBody List<StudentResponseDto> get(){
        return service.getAll();
    }



    @DeleteMapping(path ="/{studentId}")
    public void delete(@PathVariable long studentId){
         service.delete(studentId);
    }

}
