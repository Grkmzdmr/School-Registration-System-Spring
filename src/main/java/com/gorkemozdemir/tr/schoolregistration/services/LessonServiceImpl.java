package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.repositories.LessonRepository;
import com.gorkemozdemir.tr.schoolregistration.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonServiceImpl implements LessonService{

    private LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository){

        this.lessonRepository = lessonRepository;
    }

    @Override
    public void delete(Lesson lesson) {
        this.lessonRepository.delete(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Lesson save(Lesson lesson) {
        return this.lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> findByTeacherList1AndTermmmm(Teacher teacher, Term term) {
        return this.lessonRepository.findByTeacherList1AndTermmmm(teacher,term);
    }

    @Override
    public List<Lesson> findByStudentLsAndTermmmm(Student student, Term term) {
        return this.lessonRepository.findByStudentLsAndTermmmm(student,term);
    }

    @Override
    public List<String> dersad() {
        return this.lessonRepository.dersad();
    }

    @Override
    public Lesson findLessonByDersAd(String lesson) {
        return this.lessonRepository.findLessonByDersAd(lesson);
    }


}
