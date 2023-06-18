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

public class WordReplaceTextEnglish {
	

		public  boolean processRequest(String inputFileName,String OUTPUT_FILE, String name, String date) {
			boolean flag=false;
			try {
					        
				 FileInputStream fis = new FileInputStream(inputFileName);
				  XWPFDocument  xdoc = new XWPFDocument(fis);
				  flag= setHeader(xdoc, "Name", "Name: "+name);
				  System.out.println(name+" --Name-- flag "+flag);
				  flag  =setHeader(xdoc, "Date", "Date: "+date);
				  System.out.println("Date-- flag "+flag);

				  System.out.print("====================creating file==================");
				 saveDocument(xdoc, OUTPUT_FILE);
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
	    		if (!table.isEmpty())
	    		flag= replaceInTable(table, token.trim(), textToReplace.trim());
	    		if (flag){
	    			break;
	    		}
			}
	    	
	    	//////////////////////
	    	
	       /* XWPFHeaderFooterPolicy policy= document.getHeaderFooterPolicy();
	        XWPFHeader header = policy.getFirstPageHeader();
	        List<XWPFTable> table=  header.getTables();
	        replaceInTable(table, token, textToReplace);*/
	        return flag;
	    }

	    private boolean replaceInTable(List<XWPFTable> table, String placeHolder, String replaceText){
	    
	    	boolean flag=false;
	        for (XWPFTable xwpfParagraph : table) {
	        	 List<XWPFTableRow> rows = xwpfParagraph.getRows();
	            for (XWPFTableRow row : rows) {
	            	XWPFTable r= row.getTable();
	            	
	            	XWPFTableRow innerRow =  r.getRows().get(0);
	            	/*for ( XWPFTableRow row1 : innerRow )
	            	{*/ 
	            			            	
		            	for ( XWPFTableCell cell : innerRow.getTableCells() )
		            	{ 
		            		List<XWPFTable> table1=cell.getTables(); 
		            		 for (XWPFTable xwpfParagraph1 : table1) {
			            	{
			            		List<XWPFTableRow> rows1 = xwpfParagraph1.getRows();
			    	            for (XWPFTableRow row2 : rows1) {
			    	            	for ( XWPFTableCell cell1 : row2.getTableCells() )
					            	{ 
			    	            		System.out.println(placeHolder+"==="+cell1.getText());
			    	            		if (cell1.getText().trim().startsWith(placeHolder.trim())){
			    	            			cell1.removeParagraph(0);
			    	            			cell1.setText(replaceText);
				            			flag= true;
				            			 System.out.println("==replaceText=="+replaceText);
				            			 break;
				            		}
			    	            		if (cell1.getTables().size()>0){
			    	            			
			    	            		}
					            	}
			    	            }
			            	}
		            		 }
		            			
		            		
		            		
		            	//}
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
	        try (FileOutputStream out = new FileOutputStream(file)) {
	            doc.write(out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
	    
	    