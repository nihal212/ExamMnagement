package com.ensah.app.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
public enum TypeExam {
	
	    DEVOIR_SURVEILLE_1,
	    DEVOIR_SURVEILLE_2;
	  @Id
	    private Long id;
	}

