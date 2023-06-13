package com.mindzone.service.worksheets;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ListFilesUtil {
    
    public void listFilesAndFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            System.out.println(file.getName());
        }
    }
  
    public void listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getName());
            }
        }
    }
  
    public void listFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }

    public Map<String,String> listFilesAndFilesSubDirectories(String directoryName, Map<String,String> map){
        File directory = new File(directoryName);
        //get all the files from a directory
       
        
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
            	String str[] = file.getName().split("\\.");
            	//System.out.print("*"+directory.getName()+"=");
            	map.put(str[0], file.getAbsolutePath());
            	//System.out.print(str[0]+"=");
               // System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath(),map);
            }
        }
       // System.out.println("--------------"+map.size());
        return map;
    }
    public static void main (String[] args){
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        final String directoryWindows ="C:\\Users\\sajid\\Google Drive\\HomeWork\\StudyMaterial\\Math";
        Map<String,String> map= new HashMap<String,String>();
        listFilesUtil.listFilesAndFilesSubDirectories(directoryWindows,map);
    }
}
