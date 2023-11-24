package com.mindzone.service.worksheets;

import com.mindzone.dto.WorksheetsDto;
import com.mindzone.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class GenerateNewWorksheetsImpl implements  GenerateNewWorksheets {
    @Autowired
    private StudentService service;
    private String filePath = "";
    String message_return;
    static String date = "12-15-2018";
    List<WorksheetsDto> worksheetsDtoList = new ArrayList<>();


    public String generateWeeklyWorksheet(String center) {


        try {
            CommonUtil util=new CommonUtil();
            Properties prop=util.readProperties();
           // String directoryWindows = "C://sajid//MindZoneLearning//study Material//Math_Final";
            String directoryWindows =prop.get("math.worksheets.folder").toString();
            filePath = prop.get("worksheet.generate.folder").toString();
            ListFilesUtil listFilesUtil = new ListFilesUtil();
            Map<String, String> fileNameMap = new HashMap<String, String>();
            List<String> errorFile = new ArrayList<String>();
            String filecount = "1";

            fileNameMap = listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows, fileNameMap);
            worksheetsDtoList = service.getStudentWorksheetByStatus("New",center);
            if (worksheetsDtoList.size()<1){
                return "Nothing to Print";
            }
            for (int i = 0; i < worksheetsDtoList.size(); i++) {
                WorksheetsDto worksheetsDto = worksheetsDtoList.get(i);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String weekDate=worksheetsDto.getWeekDate().toString();
                String subject=worksheetsDto.getSubject();
                java.util.Date date = sdf.parse(weekDate);

                java.sql.Date sqlDate = new Date(date.getTime());

                filePath=filePath+"/"+subject;
                String folderPath= createDirectory(filePath,worksheetsDto.getWeekDate().toString());
                if (folderPath.equalsIgnoreCase("Error")){
                    worksheetsDto.setMessage("Folder Creating Error");
                    service.updateWeeklyWorksheet(worksheetsDto);
                    return "Folder Creating Error";
                }

                filePath=folderPath;
                message_return=folderPath;


                String studentName = worksheetsDto.getStudentName();
                String worksheetPath = worksheetsDto.getWorksheet();
                String extraWorksheetPath = worksheetsDto.getExtraWorksheet();
                if (worksheetsDto.getSubject().equalsIgnoreCase("English")){
                    directoryWindows = prop.get("english.worksheets.folder").toString();
                }
                String fullFileName = "";
                if (worksheetPath != null) {
                     fullFileName = studentName + "_" + filecount + "_" + worksheetPath + "_DT_" + weekDate + ".docx";
                    GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, worksheetPath, false, extraWorksheetPath,  "" + filecount,fullFileName);
                    worksheetsDto.setWorksheetPath(subject+"/"+weekDate+"/"+fullFileName);

                        fullFileName = studentName + "_" + filecount + "_" + worksheetPath + "_DT_" + weekDate + "_Answer.docx";
                        GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, worksheetPath + "_Answer", true, extraWorksheetPath,  "" + filecount,fullFileName);
                        worksheetsDto.setWorksheetPathAnswer(subject+"/"+weekDate+"/"+fullFileName);

                }

                if (extraWorksheetPath != null) {
                    fullFileName = studentName + "_" + filecount + "_" + extraWorksheetPath + "_DT_" + weekDate + ".docx";
                    GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, extraWorksheetPath, true, extraWorksheetPath, filecount + "_0",fullFileName);
                    worksheetsDto.setExtraWorksheetPath(subject+"/"+weekDate+"/"+fullFileName);

                        fullFileName = studentName + "_" + filecount + "_" + extraWorksheetPath + "_DT_" + weekDate + "_Answer.docx";
                        GenerateFile(worksheetsDto,fileNameMap, errorFile, studentName, weekDate, weekDate, extraWorksheetPath + "_Answer", true, extraWorksheetPath,  filecount + "_0",fullFileName);
                        worksheetsDto.setExtraWorksheetPathAnswer(subject+"/"+weekDate+"/"+fullFileName);

                }
                worksheetsDto.setMessage("Success");
                worksheetsDto.setStatus("Print");
                service.updateWeeklyWorksheet(worksheetsDto);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "generated";
    }
    private  void GenerateFile(WorksheetsDto worksheetsDto , Map<String, String> fileNameMap,
                                     List<String> errorFile, String studentName, String date,
                                     String date_print, String filename,boolean extra,String filenameExtra, String filecount,String fullFileName) {
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
        boolean isDirCreated =true;
        File dir = new File(strPath);
        boolean dirExists=dir.exists();
        if (!dirExists) {
            boolean isDir = dir.isDirectory();
            if (!isDir){
                isDirCreated = dir.mkdir();
            }
        }


    System.out.println(dir.getAbsolutePath());
        if(isDirCreated)
            return  strPath;
        else
        return "Error";
    }
}


