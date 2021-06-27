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

public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donemId;
    private String donem;


    @ManyToMany
    @JoinTable(name="teachers_terms",
            joinColumns=@JoinColumn(name = "egitmeniid"),
            inverseJoinColumns = @JoinColumn(name = "termid"))
    private List<Teacher> teachers1 ;

    @ManyToMany(mappedBy = "termssds",cascade = CascadeType.ALL)
    private List<Student> studentList;
     @ManyToMany(mappedBy = "termmmm",cascade = CascadeType.ALL)
    private List<Lesson> lessonList3;







}
