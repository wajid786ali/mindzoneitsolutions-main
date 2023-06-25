package com.mindzone.service.worksheets;

import com.mindzone.dto.WorksheetsDto;
import com.mindzone.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GenerateNewWorksheetsImpl implements  GenerateNewWorksheets {
    @Autowired
    private StudentService service;
    static String filePath = null;
    String message_return;
    static String date = "12-15-2018";
    List<WorksheetsDto> worksheetsDtoList = new ArrayList<>();

    public String generateWeeklyWorksheet(String weekDate,String subject) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(weekDate);

            java.sql.Date sqlDate = new Date(date.getTime());
            worksheetsDtoList = service.findByInsertDate(sqlDate);
            filePath="..";
            String folderPath= createDirectory(filePath,weekDate);
            if (folderPath.equalsIgnoreCase("Error")){
                return "Folder Creating Error";
            }
            filePath=folderPath;
            message_return=folderPath;
            String directoryWindows = "C://sajid//MindZoneLearning//study Material//Math_Final";
            ListFilesUtil listFilesUtil = new ListFilesUtil();
            Map<String, String> fileNameMap = new HashMap<String, String>();
            List<String> errorFile = new ArrayList<String>();
            String excelFilePath = null;
            String filecount = "1";
            boolean math = true;
            boolean generateAnswer = true;
            boolean generateWorksheet = true;

            fileNameMap = listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows, fileNameMap);

            for (int i = 0; i < worksheetsDtoList.size(); i++) {
                WorksheetsDto worksheetsDto = worksheetsDtoList.get(i);

                String studentName = worksheetsDto.getStudentName();
                String worksheetPath = worksheetsDto.getWorksheet();
                String extraWorksheetPath = worksheetsDto.getExtraWorksheet();
                if (worksheetsDto.getSubject().equalsIgnoreCase("English")){
                    directoryWindows = "C://sajid//MindZoneLearning//study Material//English";
                }
                String fullFileName = "";
                if (worksheetPath != null) {
                     fullFileName = studentName + "_" + filecount + "_" + worksheetPath + "_" + weekDate + ".docx";
                    GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, worksheetPath, false, extraWorksheetPath, math, "" + filecount,fullFileName);
                    worksheetsDto.setWorksheetPath(folderPath+"/"+fullFileName);
                    if (generateAnswer) {
                        fullFileName = studentName + "_" + filecount + "_" + worksheetPath + "_" + weekDate + "_Answer.docx";
                        GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, worksheetPath + "_Answer", true, extraWorksheetPath, math, "" + filecount,fullFileName);
                        worksheetsDto.setWorksheetPathAnswer(folderPath+"/"+fullFileName);
                    }
                }

                if (extraWorksheetPath != null) {
                    fullFileName = studentName + "_" + filecount + "_" + extraWorksheetPath + "_" + weekDate + ".docx";
                    GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, extraWorksheetPath, true, extraWorksheetPath, math, filecount + "_0",fullFileName);
                    worksheetsDto.setExtraWorksheetPath(folderPath+"/"+fullFileName);
                    if (generateAnswer && subject.equalsIgnoreCase("English")) {
                        fullFileName = studentName + "_" + filecount + "_" + extraWorksheetPath + "_" + weekDate + "_Answer.docx";
                        GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, extraWorksheetPath + "_Answer", true, extraWorksheetPath, math, filecount + "_0",fullFileName);
                        worksheetsDto.setExtraWorksheetPathAnswer(folderPath+"/"+fullFileName);
                    }
                }
                service.updateWeeklyWorksheet(worksheetsDto);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "generated";
    }
    private  void GenerateFile(WorksheetsDto worksheetsDto , Map<String, String> fileNameMap,
                                     List<String> errorFile, String studentName, String date,
                                     String date_print, String filename,boolean extra,String filenameExtra, boolean math, String filecount,String fullFileName) {
        String generateFileName = fileNameMap.get(filename);
        //System.out.println("=="+filename+" file name:"+generateFileName);
        if (generateFileName != null){
            WordReplaceTextEnglish doc= new WordReplaceTextEnglish();
            WordReplaceText doc1= new WordReplaceText();

            boolean flag=doc.processRequest(generateFileName, filePath + "/" + fullFileName,studentName,date_print);
         //   System.out.println(fileNameMap.get(filename)+", c://temp//"+studentName+"_"+filename+"_"+date+".docx, "+studentName+","+date);
            if (!flag){
                flag=doc1.processRequest(generateFileName,filePath+"/"+fullFileName,studentName,date_print);
            }
            if (!flag){
                errorFile.add(studentName+"-flag--"+filename);
            }

        } else if (filename != null && filename.length()>2) {
            errorFile.add(studentName+"--miss--"+filename);
        }
    }

    private  String createDirectory (String filePath,String directory) throws IOException {
        String strPath = filePath+"/"+directory;
        File dir = new File(strPath);
        boolean isDir = dir.isDirectory();
        boolean isDirCreated =true;
        if (!isDir){
            isDirCreated = dir.mkdir();
        }
    System.out.println(dir.getAbsolutePath());
        if(isDirCreated)
            return  strPath;
        else
        return "Error";
    }
}


