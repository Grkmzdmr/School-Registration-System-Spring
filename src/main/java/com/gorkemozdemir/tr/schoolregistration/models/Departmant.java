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
public class Departmant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bolumID;
    private String bolumAd;
    private String bolumYariyil;


  @ManyToMany(mappedBy = "departmants",cascade = CascadeType.ALL)
    private List<Lesson> lessonList5;


}
