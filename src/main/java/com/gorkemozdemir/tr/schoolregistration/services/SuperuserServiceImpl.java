package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Superuser;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.repositories.StudentRepository;
import com.gorkemozdemir.tr.schoolregistration.repositories.SuperuserRepository;
import com.gorkemozdemir.tr.schoolregistration.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuperuserServiceImpl implements SuperuserService{

    private SuperuserRepository superuserRepository;

    @Autowired
    public SuperuserServiceImpl(SuperuserRepository superuserRepository) {
        super();
        this.superuserRepository = superuserRepository;
    }
    @Override
    public Superuser login(String email, String password) {
        List<Superuser> result = superuserRepository.findByEmailAndPassword(email,password);
        if(result.size()==0){
            return new Superuser();
        }
        return result.get(0);
    }
}
