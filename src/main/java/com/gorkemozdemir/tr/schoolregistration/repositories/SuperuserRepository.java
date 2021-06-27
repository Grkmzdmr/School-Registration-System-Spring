package com.gorkemozdemir.tr.schoolregistration.repositories;

import com.gorkemozdemir.tr.schoolregistration.models.Superuser;
import com.gorkemozdemir.tr.schoolregistration.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuperuserRepository extends JpaRepository<Superuser,Integer>, CrudRepository<Superuser,Integer> {
    List<Superuser> findByEmailAndPassword(String email, String password);
}
