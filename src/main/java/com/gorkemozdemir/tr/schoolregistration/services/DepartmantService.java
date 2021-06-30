package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.models.Student;

import java.util.List;

public interface DepartmantService {
    List<Departmant> getAll();

    Departmant save(Departmant departmant);
    List<String> bolumAd();
    Departmant delete(Departmant departmant);
    Departmant findDepartmantByBolumAd(String bolumad);

}


