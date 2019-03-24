package com.bintech.kooswimming.service;

import com.bintech.kooswimming.entriy.SignUp;
import com.bintech.kooswimming.exception.CreateExelException;
import com.bintech.kooswimming.exception.DownloadFileError;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    @Autowired
    SignUpService signUpService;

    @Value("${filepath}")
    private String filepath;


    public void exportAllAsExcel(String filename)  {
        List<SignUp> signUps = signUpService.getAllSignUps();
        List<String> headers = getObjectFields(signUps.get(0));
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sign Ups");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle =  workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        int headerCount = 0;
        Cell headerCell;
        for (String headerString : headers) {
            headerCell = header.createCell(headerCount);
            headerCell.setCellValue(headerString);
            headerCell.setCellStyle(headerStyle);
            headerCount += 1;
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int rowCount = 1;
        for(SignUp signUp : signUps) {
            Row row = sheet.createRow(rowCount);
            Cell cell;
            Map<String, Object> props;
            try {
                props = PropertyUtils.describe(signUp);
            } catch (Exception ex) {

                throw new CreateExelException("error creating excel file", ex);
            }

            int columnCount = 0;

            for (String headerName: headers) {
                cell = row.createCell(columnCount);
                cell.setCellValue((String) props.get(headerName));
                columnCount += 1;
            }
            rowCount += 1;
        }

        String path = Paths.get(filepath).resolve(filename).toString();

        //File currDir = new File(".");
        // String path = currDir.getAbsolutePath();
        //String fileLocation = path.substring(0, path.length() - 1) + filename;

        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception ex) {
            throw new CreateExelException("error creating excel file", ex);
        }

    }


    public List<String> getObjectFields(Object o) {
        Class objectClass = o.getClass();
        List<Field> fields = Arrays.asList(objectClass.getDeclaredFields());

        List<String> fieldNames =  new ArrayList<>();

        fields.forEach(x-> fieldNames.add(x.getName()));
        return fieldNames;
    }



    public Resource loadFileAsResourse(String filename) {
        try {
            Path path = Paths.get(filepath).resolve(filename);

            return new UrlResource(path.toUri());
        } catch (MalformedURLException ex) {
            throw new DownloadFileError("error downloading excel file:", ex);
        }


    }

}
