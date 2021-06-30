package com.gorkemozdemir.tr.schoolregistration.services;


import com.gorkemozdemir.tr.schoolregistration.models.*;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student save(Student student);
    void delete(Student student);
    Student login(String email, String password);
    List<Student> findByTeachersAndTermssds(Teacher teacher, Term term);
    List<Student> findStudentByDepartmant(Departmant departmant);
    String countStudentByDepartmant(Departmant departmant);
    List<Student > findByLessons(Lesson lesson);

}
