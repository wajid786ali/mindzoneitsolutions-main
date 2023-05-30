package com.mindzone.controller;

import com.mindzone.dto.*;
import com.mindzone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "/addTeachers", method = RequestMethod.POST)
    public @ResponseBody String  addTeachers( @RequestBody TeachersDto sdto){
        return service.addTeachers(sdto);
    }


    @RequestMapping(value = "/addStudentNotes", method = RequestMethod.POST)
    public @ResponseBody String  addStudentNotes( @RequestBody StudentNotesDto sdto){
        return service.addStudentNotes(sdto);
    }
    @GetMapping(path ="/listReminders")
    public @ResponseBody List<StudentNotesDto> getListReminders(){
        return service.getStudentNotes();
    }
    @GetMapping(path ="/listTeachers")
    public @ResponseBody List<TeachersDto> getListTeachers(){
        return service.getListTeachers();
    }
    @GetMapping(path ="/listFeedBack")
    public @ResponseBody List<StudentFeedBackDto> getFeedback(){
        return service.getFeedBacks();
    }

 /*  @GetMapping(path ="/listWorksheet")
   public @ResponseBody List<WorksheetsDto> listWorksheet(){
        return service.getListWorksheet();
   }
*/
    @GetMapping(path ="/listWorksheet")
    public @ResponseBody WorksheetListDto listWorksheet(){
        WorksheetListDto wdto = new WorksheetListDto();
        Set<String> wsSet= new HashSet();
        List<WorksheetsDto> list= service.getListWorksheet();
        for (int i= 0; i<list.size();i++){
            WorksheetsDto ws= list.get(i);
            wsSet.add(ws.getWeekDate().toString());
        }
        wdto.setWorksheetsDtoList(list);
        wdto.setWeeklyDate(wsSet);
        return wdto;
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
