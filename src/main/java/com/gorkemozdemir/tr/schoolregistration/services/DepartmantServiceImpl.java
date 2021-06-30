package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Departmant;
import com.gorkemozdemir.tr.schoolregistration.models.Lesson;
import com.gorkemozdemir.tr.schoolregistration.repositories.DepartmantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmantServiceImpl implements DepartmantService{
    private DepartmantRepository departmantRepository;

    @Autowired
    public DepartmantServiceImpl(DepartmantRepository departmantRepository){
        this.departmantRepository = departmantRepository;
    }
    @Override
    public List<Departmant> getAll() {
        return this.departmantRepository.findAll();
    }

    @Override
    public Departmant save(Departmant departmant) {
        return this.departmantRepository.save(departmant);
    }

    @Override
    public List<String> bolumAd() {
        return this.departmantRepository.bolumAd();
    }

    @Override
    public Departmant delete(Departmant departmant) {
        this.departmantRepository.deleteById(departmant.getBolumID());
        return departmant;
    }

    @Override
    public Departmant findDepartmantByBolumAd(String bolumad) {
        return this.departmantRepository.findDepartmantByBolumAd(bolumad);
    }


}
