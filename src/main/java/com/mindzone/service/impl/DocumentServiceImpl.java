package com.mindzone.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mindzone.service.DocumentService;
import lombok.extern.slf4j.Slf4j;

import com.mindzone.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public void docToPdf(String fileName) {
        String[] fileAnswer=fileName.split("_Answer");
        String[] fileParts=fileName.split("_DT_");
        String[] strDate=fileParts[1].split("\\.");
        String filePath="../Math/"+strDate[0];
       File file = new File("../Math/"+strDate[0]+"/"+fileName);
       // String  fileNameWithExtension = fileName.split("\\.")[0];
        try (
                //   InputStream docxInputStream = Files.newInputStream("../Math/"+strDate[0]+"/"+fileName);
                InputStream docxInputStream = new BufferedInputStream(new FileInputStream(file));
                XWPFDocument document = new XWPFDocument(docxInputStream);

                OutputStream pdfOutputStream = Files.newOutputStream(Paths.get(filePath+"/"+ fileName+".pdf"))) {
            Document pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, pdfOutputStream);
            pdfDocument.open();

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                pdfDocument.add(new Paragraph(paragraph.getText()));
            }
            pdfDocument.close();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }

}


    @Override
    public void download(String fileName) {
        String[] fileAnswer=fileName.split("_Answer");
        String[] fileParts=fileName.split("_DT_");
        String[] strDate=fileParts[1].split("\\.");
        try {
            File file = new File("../Math/"+strDate[0]+"/"+fileName);
            if (file.exists()) {
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                response.setContentLength((int) file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());

            }
        } catch (IOException ex) {
            log.error("File " + fileName + " Not Found::", ex);
            throw new RuntimeException("File " + fileName + " Not Found");
        }
    }

}
