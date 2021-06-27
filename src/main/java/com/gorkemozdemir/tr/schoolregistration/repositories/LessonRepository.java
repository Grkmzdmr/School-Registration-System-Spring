package com.gorkemozdemir.tr.schoolregistration.repositories;

import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer>, CrudRepository<Lesson,Integer> {
    List<Lesson> findByTeacherList1AndTermmmm(Teacher teacher,Term term);
    List<Lesson> findByStudentLsAndTermmmm(Student student, Term term);
    @Query(value = "Select t.dersAd from Lesson t")
    List<String> dersad();
}
