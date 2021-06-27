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
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int egitmenID;
    private String egitmenAd;
    private String egitmenSoyad;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name="teacherrr_students",
            joinColumns=@JoinColumn(name = "egitmenid"),
            inverseJoinColumns = @JoinColumn(name = "ogrenciid"))
    private List<Student> studentsss ;


    @ManyToMany(mappedBy = "teacherList1")
    private List<Lesson> lessons;


    @ManyToMany(mappedBy = "teachers1")
    private List<Term> termss;





}
