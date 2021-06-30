package com.gorkemozdemir.tr.schoolregistration.repositories;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmantRepository extends JpaRepository<Departmant,Integer> {
    @Query(value = "Select t.bolumAd from Departmant t")
    List<String> bolumAd();

    Departmant findDepartmantByBolumAd(String bolumad);
}
