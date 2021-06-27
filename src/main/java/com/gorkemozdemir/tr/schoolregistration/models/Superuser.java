package com.gorkemozdemir.tr.schoolregistration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Superuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spID;
    private String email;
    private String password;

}
