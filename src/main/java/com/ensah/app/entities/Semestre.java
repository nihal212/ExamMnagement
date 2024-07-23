package com.ensah.app.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public enum Semestre {
	  
    PRINTEMPS,
    AUTOMNE;
    @Id
    private Long id;
}

