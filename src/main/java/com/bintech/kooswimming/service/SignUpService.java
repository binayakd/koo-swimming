package com.bintech.kooswimming.service;

import com.bintech.kooswimming.entriy.SignUp;

import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SignUpService {

    @Autowired
    private ObjectRepository<SignUp> signUpRepository;

    public List<Long> createSignUp(SignUp signUp) {
        List<Long> idList = new ArrayList<>();
        WriteResult result = signUpRepository.insert(signUp);

        result.forEach(x -> idList.add(x.getIdValue()));

        return idList;
    }

    public List<SignUp> getAllSignUps() {

        List<SignUp> signUpsList = new ArrayList<>();
        Cursor<SignUp> cursor = signUpRepository.find();
        cursor.forEach(signUpsList::add);

        return signUpsList;
    }

}
