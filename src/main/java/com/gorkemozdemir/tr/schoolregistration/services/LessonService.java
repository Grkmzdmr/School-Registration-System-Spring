package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;

import java.util.List;

public interface LessonService {
    void delete(Lesson lesson);
    List<Lesson> getAll();
    Lesson save(Lesson lesson);
    List<Lesson> findByTeacherList1AndTermmmm(Teacher teacher,Term term);
    List<Lesson> findByStudentLsAndTermmmm(Student student, Term term);
    List<String> dersad();

}
