package com.gorkemozdemir.tr.schoolregistration.repositories;

import com.gorkemozdemir.tr.schoolregistration.models.Term;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term,Integer>{
    @Query(value = "Select t.donem from Term t")
    List<String> donemad();
    Term findByDonem(String donem);

}
