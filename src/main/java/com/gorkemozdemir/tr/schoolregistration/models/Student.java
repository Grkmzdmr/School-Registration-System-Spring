package com.gorkemozdemir.tr.schoolregistration.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private int ogrenciID;
    private String ogrenciAd;
    private String ogrenciSoyad;
    private String ogrenciNo;
    private String email;
    private String password;


    @ManyToMany(mappedBy = "studentsss")
    private List<Teacher> teachers;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="termsssd_students",
            joinColumns=@JoinColumn(name = "studentidd"),
            inverseJoinColumns = @JoinColumn(name = "termidd"))
    private List<Term> termssds ;


   @ManyToMany(mappedBy = "studentLs")
    private List<Lesson> lessons;




}
