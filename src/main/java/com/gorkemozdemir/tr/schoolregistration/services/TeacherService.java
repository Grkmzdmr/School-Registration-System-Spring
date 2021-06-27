package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Teacher;

import java.util.List;

public interface TeacherService  {
    List<Teacher> getAll();
    Teacher save(Teacher teacher);
    void delete(Teacher teacher);
    Teacher login(String email,String password);


}
