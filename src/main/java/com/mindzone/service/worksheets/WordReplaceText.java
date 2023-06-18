package com.mindzone.service.worksheets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordReplaceText {
	

		public  boolean processRequest(String inputFileName,String OUTPUT_FILE, String name, String date) {
			boolean flag=false;
			try {
					        
				 FileInputStream fis = new FileInputStream(inputFileName);
				  XWPFDocument  xdoc = new XWPFDocument(fis);
				  flag= setHeader(xdoc, "Name", "Name: "+name);
				  System.out.println(name+" --Name-- flag "+flag);
				boolean  flag1  =setHeader(xdoc, "Date", "Date: "+date);
				  System.out.println("Date-- flag "+flag);
				 //if (flag && flag1) {
					 System.out.print("====================creating file==================");
				 saveDocument(xdoc, OUTPUT_FILE);
				 //} else{
				//	 //saveDocument(xdoc, OUTPUT_FILE+"------.docx");
				// }
			     } catch(Exception ex) {
				ex.printStackTrace();
			     }
			return flag;
		}
	    
	    public boolean setHeader(XWPFDocument document, String token, String textToReplace){
	    	 boolean flag=false;
	    	///////////////////////////
	    	XWPFHeaderFooterPolicy policy= document.getHeaderFooterPolicy();
	    	List<XWPFHeader> headers=document.getHeaderList();
	    	for (int i = 0; i < headers.size(); i++) {
	    		XWPFHeader header= headers.get(i);
	    		List<XWPFTable> table=  header.getTables();
	    		flag= replaceInTable(table, token.trim(), textToReplace.trim());
			}
	    	

	        return flag;
	    }

	    private boolean replaceInTable(List<XWPFTable> table, String placeHolder, String replaceText){
	    
		boolean flag = false;
		for (XWPFTable xwpfParagraph : table) {
			List<XWPFTableRow> rows = xwpfParagraph.getRows();
			for (XWPFTableRow row : rows) {
				for (XWPFTableCell cell : row.getTableCells()) {
					// System.out.println(placeHolder+"==cell.getText()=="+cell.getText());
					if (cell.getText().trim().startsWith(placeHolder.trim())) {
						cell.removeParagraph(0);
						cell.setText(replaceText);
						flag = true;
						System.out.println("==replaceText==" + replaceText);
					}
				}
			}
		}
	        return flag;
	        }

	    
	    
	    private void replaceInParagraphs1(List<XWPFParagraph> paragraphs, String placeHolder, String replaceText){
	    	System.out.println(placeHolder +"============="+replaceText);
	        for (XWPFParagraph xwpfParagraph : paragraphs) {
	            List<XWPFRun> runs = xwpfParagraph.getRuns();
	            for (XWPFRun run : runs) {
	                String runText = run.getText(run.getTextPosition());
	                System.out.println("************"+runText);
	                if(placeHolder !="" && !placeHolder.isEmpty()){
	                    if(runText != null &&
	                            Pattern.compile(placeHolder, Pattern.CASE_INSENSITIVE).matcher(runText).find()){
	                        runText = replaceText;
	                        System.out.println("====================="+runText);
	                    }
	                }
	                run.setText(runText, 0);
	            }
	        }
	        
	    }
	        
	    private void replaceInParagraphs(List<XWPFParagraph> paragraphs, String placeHolder, String replaceText){
	    	System.out.println(placeHolder +"============="+replaceText);
	        for (XWPFParagraph xwpfParagraph : paragraphs) {
	            List<XWPFRun> runs = xwpfParagraph.getRuns();
	            for (XWPFRun run : runs) {
	                String runText = run.getText(run.getTextPosition());
	                System.out.println("************"+runText);
	                if(placeHolder !="" && !placeHolder.isEmpty()){
	                    if(runText != null &&
	                            Pattern.compile(placeHolder, Pattern.CASE_INSENSITIVE).matcher(runText).find()){
	                        runText = replaceText;
	                        System.out.println("====================="+runText);
	                    }
	                }
	                run.setText(runText, 0);
	            }
	        }
	    }
	    
	    
	    private void saveDocument(XWPFDocument doc, String file) {
	    	
	    	//XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(lowerFilePath));
            System.out.println("================***=="+doc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages());
            
            
	        try (FileOutputStream out = new FileOutputStream(file)) {
	            doc.write(out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
	    
	    