package com.mindzone.service.impl;

import com.mindzone.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


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
