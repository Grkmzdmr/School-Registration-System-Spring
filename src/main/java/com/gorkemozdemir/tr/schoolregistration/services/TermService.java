package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import com.gorkemozdemir.tr.schoolregistration.models.Term;

import java.util.List;

public interface TermService {
    List<String> donemad();
    List<Term> getAll();
    Term save(Term term);
    void delete(Term term);
    Term findByDonem(String donem);
}
