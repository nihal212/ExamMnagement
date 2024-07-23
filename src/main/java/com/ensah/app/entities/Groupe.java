package com.ensah.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class Groupe {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idGroupe;
	 private String title;
	    
	 @OneToMany(mappedBy = "groupe")
	    @JsonIgnore

	    private List<Enseignant> enseignants;

	
	 
}
