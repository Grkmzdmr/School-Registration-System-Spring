package com.gorkemozdemir.tr.schoolregistration.repositories;

import com.gorkemozdemir.tr.schoolregistration.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer>, CrudRepository<Student,Integer> {
    List<Student> findByEmailAndPassword(String email, String password);
    List<Student> findByTeachersAndTermssds(Teacher teacher, Term term);
    List<Student > findByLessons(Lesson lesson);
    List<Student> findStudentByDepartmant(Departmant departmant);
    String countStudentByDepartmant(Departmant departmant);

}
