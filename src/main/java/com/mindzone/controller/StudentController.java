package com.mindzone.controller;

import com.mindzone.dto.*;
import com.mindzone.service.StudentService;
import com.mindzone.service.worksheets.GenerateNewWorksheets;
import com.mindzone.service.worksheets.NextWeekWorksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.mindzone.utils.MZConstants.STUDENTS;
import static com.mindzone.utils.MZConstants.URI;

@RestController
@RequestMapping(path = URI+ STUDENTS)
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private NextWeekWorksheet nextWeekWorksheet;

    @Autowired
    private GenerateNewWorksheets generateNewWorksheets;

    @PostMapping
    public @ResponseBody StudentResponseDto create(@RequestBody StudentRequestDto studentRequestDto){
    return service.create(studentRequestDto);
    }

    @RequestMapping(value = "/addFeedBack", method = RequestMethod.POST)
    public @ResponseBody String  addFeedBack( @RequestBody StudentFeedBackDto sdto){
       return service.addFeedBack(sdto.getStudentId(), sdto.getStudentName(), sdto.getNoOfWorksheets(), sdto.getComments(), sdto.getWorksheetsType(),sdto.getTeacherName());
    }
    @RequestMapping(value = "/addWeeklyWorksheet", method = RequestMethod.POST)
    public @ResponseBody String  addWeeklyWorksheet( @RequestBody List<WorksheetsDto> sdto){
         return service.addWeeklyWorksheet(sdto);
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
    @GetMapping(path ="/listRemindersNew")
    public @ResponseBody List<StudentNotesDto> getListRemindersNew(){
        return service.getStudentNotes("New");
    }

    @GetMapping(path ="/listRemindersResolve")
    public @ResponseBody List<StudentNotesDto> getListRemindersResolved(){
        return service.getStudentNotes("Resolved");
    }

    @DeleteMapping(path ="/reminderDelete/{studentId}")
    public void reminderDelete(@PathVariable String studentId){
        service.reminderDelete(studentId);
    }

    @DeleteMapping(path ="/deleteTeachers/{email}")
    public void deleteTeachers(@PathVariable String email){
        service.teacherDelete(email);
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

    @GetMapping(path ="/listWorksheetbyWeekDate/{weekDate}")
    public @ResponseBody List<WorksheetsDto> listWorksheetbyWeekDate(@PathVariable String weekDate)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(weekDate);

            java.sql.Date sqlDate = new Date(date.getTime());
            List<WorksheetsDto> list=service.findByInsertDate(sqlDate);
            return list;
        }catch (Exception e){
          e.printStackTrace();
        }
       return null;
    }

    @GetMapping(path ="/listWorksheetbyMonth/{month}")
    public @ResponseBody List<WorksheetsDto> listStudentWorksheet(@PathVariable String month)
        {
            return service.getStudentWorksheet(month);
        }

    @GetMapping(path ="/newWeeklyWorksheets/{weeklyDate}/{subject}")
    public @ResponseBody List<WorksheetsDto>  newWeeklyWorksheets(@PathVariable String weeklyDate,@PathVariable String subject){
        return nextWeekWorksheet.homeworkGenerator(weeklyDate,subject);
    }

    @GetMapping(path ="/generateWeeklyWorksheets/{weekDate}/{subject}")
    public @ResponseBody String generateWeeklyWorksheets(@PathVariable String weekDate,@PathVariable String subject){
        return generateNewWorksheets.generateWeeklyWorksheet(weekDate,subject);
    }


    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    public @ResponseBody UserNameDto userName(@RequestBody UserNameDto userNameDto){
        return service.userName(userNameDto.getEmail(),userNameDto.getPassword());
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
