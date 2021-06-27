package com.gorkemozdemir.tr.schoolregistration.services;


import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;

import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {

        return this.studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);

    }

    @Override
    public Student login(String email, String password) {
        List<Student> result = studentRepository.findByEmailAndPassword(email,password);
        if(result.size()==0){
            return new Student();
        }
        return result.get(0);
    }

    @Override
    public List<Student> findByTeachersAndTermssds(Teacher teacher, Term term) {
        return this.studentRepository.findByTeachersAndTermssds(teacher,term);
    }

    @Override
    public List<String> findByLessons(Lesson lesson) {
        return this.studentRepository.findByLessons(lesson);
    }


}
