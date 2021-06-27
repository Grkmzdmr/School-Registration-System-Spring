package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Term;
import com.gorkemozdemir.tr.schoolregistration.repositories.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TermServiceImpl implements TermService{
    private TermRepository termRepository;

    @Autowired
    public TermServiceImpl(TermRepository termRepository){
        this.termRepository = termRepository;
    }


    @Override
    public List<String> donemad() {
        return this.termRepository.donemad();
    }

    @Override
    public List<Term> getAll() {
        return this.termRepository.findAll();

    }

    @Override
    public Term save(Term term) {
        return this.termRepository.save(term);
    }

    @Override
    public void delete(Term term) {
        this.termRepository.delete(term);
    }

    @Override
    public Term findByDonem(String donem) {
        return this.termRepository.findByDonem(donem);
    }

}
