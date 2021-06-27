package com.gorkemozdemir.tr.schoolregistration.services;


import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student save(Student student);
    void delete(Student student);
    Student login(String email, String password);
    List<Student> findByTeachersAndTermssds(Teacher teacher, Term term);
    List<String > findByLessons(Lesson lesson);

}
