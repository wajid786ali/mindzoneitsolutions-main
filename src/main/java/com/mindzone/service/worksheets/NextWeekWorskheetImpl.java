package com.mindzone.service.worksheets;

import com.mindzone.dto.StudentResponseDto;
import com.mindzone.dto.WorksheetsDto;
import com.mindzone.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class NextWeekWorskheetImpl implements  NextWeekWorksheet {

    @Autowired
    private StudentService service;
    String directoryWindows = "";


    private  List<WorksheetsDto> listLastWeek()
    {
        try {
            return service.findLastWeekyWorksheet();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, ArrayList<String>>  studentHistory() {
        List<WorksheetsDto> list = service.getListWorksheet();
        Map<String, ArrayList<String>> studentwork = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < list.size(); i++) {
            WorksheetsDto ws = list.get(i);
            if ((studentwork.containsKey(ws.getStudentName()))) {
                ArrayList<String> columndata = studentwork.get(ws.getStudentName());
                columndata.add(ws.getWorksheet());
                studentwork.put(ws.getStudentName(), columndata);
            } else {
                ArrayList<String> columndata = new ArrayList<>();
                columndata.add(ws.getWorksheet());
                studentwork.put(ws.getStudentName(), columndata);
            }
        }
    return studentwork;
    }
    @Override
    public List<WorksheetsDto> homeworkGenerator(String newWeekDate,String subject,String center,String studentName){
        List<WorksheetsDto> newWSList= new ArrayList<>();
        try {
            ListFilesUtil listFilesUtil = new ListFilesUtil();
            CommonUtil util=new CommonUtil();
            Properties prop=util.readProperties();
            boolean math = true;
            if (subject.equalsIgnoreCase("Math")) {
                directoryWindows = prop.get("math.worksheets.folder").toString();
            } else if (subject.equalsIgnoreCase("English")){
                directoryWindows = prop.get("english.worksheets.folder").toString();
            }
            Map<String, ArrayList<String>> studentOldWorksheet= studentHistory();

            List<WorksheetsDto> wsList= listLastWeek();

            Map<String, String> fileNameMap = new HashMap<String, String>();
            fileNameMap = listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows, fileNameMap);

            Map<String, WorksheetsDto> studentWorksheet = new HashMap<String, WorksheetsDto>();
            for (int i = 0; i < wsList.size(); i++) {
                if (wsList.get(i).getSubject() != null && wsList.get(i).getSubject().contains(subject)) {
                    studentWorksheet.put(wsList.get(i).getStudentName(), wsList.get(i));
                }
            }

            List<StudentResponseDto> studentList =service.getAll(center);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(newWeekDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            StudentResponseDto studentResponseDto =null;
            if (studentName.equalsIgnoreCase("selectName") || studentName.equalsIgnoreCase("undefined") ){
                for (int i = 0; i < studentList.size(); i++) {
                    WorksheetsDto wdto = new WorksheetsDto();
                     studentResponseDto = studentList.get(i);
                    processStudentsWorksheets(subject, center, newWSList, studentOldWorksheet, fileNameMap, studentWorksheet, sqlDate, wdto, studentResponseDto);
                }
            } else {
                for (int i = 0; i < studentList.size(); i++) {
                    WorksheetsDto wdto = new WorksheetsDto();
                    studentResponseDto = studentList.get(i);
                    if (studentName.equalsIgnoreCase(studentResponseDto.getStudentName())) {
                        processStudentsWorksheets(subject, center, newWSList, studentOldWorksheet, fileNameMap, studentWorksheet, sqlDate, wdto, studentResponseDto);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("main1 *" + e.toString());
        }
        return newWSList;
    }

    private void processStudentsWorksheets(String subject, String center, List<WorksheetsDto> newWSList, Map<String, ArrayList<String>> studentOldWorksheet, Map<String, String> fileNameMap, Map<String, WorksheetsDto> studentWorksheet, java.sql.Date sqlDate, WorksheetsDto wdto, StudentResponseDto studentResponseDto) {
        if (studentResponseDto.getStatus().equalsIgnoreCase("Active") &&
                studentResponseDto.getSubjects().contains(subject)) {
            String studentname = studentResponseDto.getStudentName();
            wdto.setStudentName(studentname);
            wdto.setGrade(studentResponseDto.getGrade());
            wdto.setWeekDate(sqlDate);
            wdto.setSubject(subject);
            wdto.setStatus("New");
            wdto.setCenter(center);
            WorksheetsDto filenameDto = studentWorksheet.get(studentname);
            if (filenameDto != null) {
                String filename = filenameDto.getWorksheet();
                if (filename != null) {
                    newWorksheets(fileNameMap, filename, studentOldWorksheet.get(studentname), wdto);
                }
                String filenameExtra = filenameDto.getExtraWorksheet();
                if (filenameExtra != null) {
                    newWorksheetsExtra(fileNameMap, filenameExtra, studentOldWorksheet.get(studentname), wdto);
                }
            }
            newWSList.add(wdto);
        }
    }

    private void newWorksheetsExtra(Map<String, String> fileNameMap,
                               String filename, ArrayList work, WorksheetsDto wsDto) {
        String newFile;
        if (!filename.equals("no")) {
            newFile = nextWeekFile(filename);
            if (fileNameMap.containsKey(newFile)) {
                System.out.print(newFile);
                wsDto.setExtraWorksheet(newFile);
            } else {
                newFile = findNextLevelFile(fileNameMap, filename);
                System.out.print(newFile + "^new^");
                wsDto.setExtraWorksheet(newFile);
                wsDto.setExtraWorksheetPath(newFile);
                wsDto.setWorksheetExtraStatus("New");
            }
            if (work.contains(newFile) && !newFile.equals("no")) {
                System.out.print("*old*");
                wsDto.setWorksheetExtraStatus("New:Used");
                // propose suggestions
                boolean found = true;
                while (found) {
                    newFile = findNextLevelFile(fileNameMap, newFile);
                    if (newFile.equals("no")) {
                        found = false;
                    }
                    if (!work.contains(newFile) && !newFile.equals("no")) {
                        found = false;
                        wsDto.setWorksheetExtraStatus("Used previously: Suggested");
                        System.out.print("*suggested*" + newFile);
                        wsDto.setWorksheetNewSuggested(newFile);
                    }
                }

                // end of propose suggestions
            }
            System.out.println();
        } else {
            System.out.println("no");
        }
    }
    private void newWorksheets(Map<String, String> fileNameMap,
                               String filename, ArrayList work, WorksheetsDto wsDto) {
        String newFile;
        if (!filename.equals("no")) {
            newFile = nextWeekFile(filename);
            if (fileNameMap.containsKey(newFile)) {
                System.out.print(newFile);
                wsDto.setWorksheet(newFile);
            } else {
                newFile = findNextLevelFile(fileNameMap, filename);
                System.out.print(newFile + "^new^");
                wsDto.setWorksheet(newFile);
                wsDto.setWorksheetPath(newFile);
                wsDto.setWorksheetStatus("New");
            }
            if (work.contains(newFile) && !newFile.equals("no")) {
                System.out.print("*old*");
                wsDto.setWorksheetStatus("New:Used");
                // propose suggestions
                boolean found = true;
                while (found) {
                    newFile = findNextLevelFile(fileNameMap, newFile);
                    if (newFile.equals("no")) {
                        found = false;
                    }
                    if (!work.contains(newFile) && !newFile.equals("no")) {
                        found = false;
                        wsDto.setWorksheetStatus("Used previously: Suggested");
                        System.out.print("*suggested*" + newFile);
                        wsDto.setWorksheetNewSuggested(newFile);
                    }
                }

                // end of propose suggestions
            }
            System.out.println();
        } else {
            System.out.println("no");
        }
    }

    private String findNextLevelFile(Map<String, String> fileNameMap,
                                     String fileName) {
        String returnFileName = "no";
        try {
            // added code to find next file
            // System.out.println("filename need to find new folder" +
            // fileName);
            String folderPath = fileNameMap.get(fileName);

            if (folderPath != null) {
                folderPath = folderPath.replace("\\", "/");
                String[] splittedFileName = folderPath.split("/");

                // System.out.println("filename need to find new folder"+filename);
                String parentsFolder = splittedFileName[splittedFileName.length - 3];
                String oldFolder = splittedFileName[splittedFileName.length - 2];
                String[] file = oldFolder.split("_");
                if (file.length > 0 && file[0].length() > 0) {
                    String folderNumber = file[0];
                    int folderNumbers = Integer.parseInt(folderNumber) + 1;

                    returnFileName = extractedNextLevel(parentsFolder,
                            folderNumbers);
                }
            }
        } catch (Exception e) {
            // System.out.println("main exception" + e.toString());
        }
        return returnFileName;
    }

    private String extractedNextLevel(String parentsFolder, int folderNumbers) {
        String returnFileName = "no";
        File[] directories = new File(directoryWindows + "\\" + parentsFolder)
                .listFiles(File::isDirectory);
        // System.out.println(directories);
        for (File workfolder : directories) {
            // System.out.println("Work folder:::"+workfolder.toString());
            String workfolderPath = workfolder.toString().replace("\\", "/");
            String[] splitGradeFolder = workfolderPath.toString().split("/");

            // System.out.println("filename need to find new folder"+filename);
            String parFolder = splitGradeFolder[splitGradeFolder.length - 1];
            // System.out.println("parFolder ::"+parFolder);

            if (parFolder.toString().startsWith(folderNumbers + "_")) {
                // System.out.println("******" + workfolder.toString());

                File[] fList = workfolder.listFiles();
                for (File wfile : fList) {
                    if (wfile.isFile()) {
                        String str[] = wfile.getName().split("\\.");
                        if (str[0].endsWith("_0") || str[0].endsWith("_1"))
                            // System.out.println("str[0]" + str[0]);
                            returnFileName = str[0];
                    }
                }

            }
        }
        return returnFileName;
    }

    private String nextWeekFile(String currectDayFile) {
        if (!currectDayFile.equalsIgnoreCase("no")) {
            try {
                // System.out.println(currectDayFile);
                String[] file = currectDayFile.split("_");
                if (file.length > 1) {
                    String fileName_New = "";
                    for (int i = 0; i < file.length - 1; i++) {
                        fileName_New += file[i] + "_";
                    }
                    int countFile = Integer.parseInt(file[file.length - 1]);
                    countFile++;
                    String newName = fileName_New + "" + countFile;
                    // System.out.println("next--" + newName);
                    return newName;
                }
            } catch (Exception e) {
                System.out.println("nextWeekFile" + e.toString());
            }
        }
        return currectDayFile;
    }
}
