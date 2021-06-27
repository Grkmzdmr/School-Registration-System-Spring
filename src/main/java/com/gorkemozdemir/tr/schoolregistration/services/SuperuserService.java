package com.gorkemozdemir.tr.schoolregistration.services;

import com.gorkemozdemir.tr.schoolregistration.models.Student;
import com.gorkemozdemir.tr.schoolregistration.models.Superuser;

public interface SuperuserService {
    Superuser login(String email, String password);

}
