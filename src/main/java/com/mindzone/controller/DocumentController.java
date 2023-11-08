package com.mindzone.controller;

import com.mindzone.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.mindzone.utils.MZConstants.DOCUMENT;
import static com.mindzone.utils.MZConstants.URI;

@RestController
@RequestMapping(path = URI+ DOCUMENT)
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping(path = "/{name}")
    public void download(@PathVariable(name = "name") String name){
      //  documentService.docToPdf(name);
        documentService.download(name);
    }
}
