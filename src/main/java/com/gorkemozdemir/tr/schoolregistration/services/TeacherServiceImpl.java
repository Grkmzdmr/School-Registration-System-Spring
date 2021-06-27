package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    private TeacherRepository teacherRepository;



    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository){

        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<Teacher> getAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
            teacherRepository.delete(teacher);

    }

    @Override
    public Teacher login(String email, String password) {
        List<Teacher> result = teacherRepository.findByEmailAndPassword(email,password);
        if(result.size()==0){
            return new Teacher();
        }
        return result.get(0);
    }



}
