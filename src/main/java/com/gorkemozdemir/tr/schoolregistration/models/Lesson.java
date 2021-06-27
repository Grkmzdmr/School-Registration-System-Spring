package com.gorkemozdemir.tr.schoolregistration.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int dersID;
    private String dersAd;
    private String dersYariyil;
    private String dersKredi;

    @ManyToMany
    @JoinTable(name="teacher_lessons",
            joinColumns=@JoinColumn(name = "dersid"),
            inverseJoinColumns = @JoinColumn(name = "egitmenid"))
    private List<Teacher> teacherList1;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="termsssd_lessons",
            joinColumns=@JoinColumn(name = "dersidd"),
            inverseJoinColumns = @JoinColumn(name = "termidd"))
    private List<Term> termmmm ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="lessonss_departmants",
            joinColumns=@JoinColumn(name = "dersidd"),
            inverseJoinColumns = @JoinColumn(name = "bolumidd"))
    private List<Departmant> departmants ;
    @ManyToMany
    @JoinTable(name="student_lessons",
            joinColumns=@JoinColumn(name = "dersid"),
            inverseJoinColumns = @JoinColumn(name = "ogrenciid"))
    private List<Student> studentLs;

}
