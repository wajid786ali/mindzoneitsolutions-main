package com.mindzone.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    void download(String fileName);
    void docToPdf(String fileName);
}
