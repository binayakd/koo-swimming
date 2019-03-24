package com.bintech.kooswimming.controller;

import com.bintech.kooswimming.service.ExcelService;
import com.bintech.kooswimming.service.SignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ExcelService excelService;


    @GetMapping("/admin")
    public String adminView(Model model) {
        model.addAttribute("signUpList", signUpService.getAllSignUps());

        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }



    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
        String filename = ZonedDateTime
                .now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_INSTANT)
                .replaceAll(":", ".")
                .concat(".xlsx");

        excelService.exportAllAsExcel(filename);

        Resource resource = excelService.loadFileAsResourse(filename);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);


    }

}
