package com.bintech.kooswimming.service;

import com.bintech.kooswimming.entriy.SignUp;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelServiceTest {

    @Autowired
    ExcelService excelService;

    @Before
    public void setUp(){
        //excelService = new ExcelService();
        //excelService.signUpService = new SignUpService();
    }

    @Test
    public void testExportAllAsExcel() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        excelService.exportAllAsExcel("test.xlsx");
    }

    @Test
    public void getObjectFields() {
        SignUp signUp = new SignUp();
        List<String> fieldNames = excelService.getObjectFields(signUp);
        System.out.println(fieldNames.toString()
                .replace("[","")
                .replace("]",""));
    }



    public Nitrite getNitrateDb() {
        return Nitrite
                .builder()
                .compressed()
                .filePath("db/main.db")
                .openOrCreate();

    }


    public ObjectRepository<SignUp> signUpRepository() {
        return getNitrateDb().getRepository(SignUp.class);
    }
}