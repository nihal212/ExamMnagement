package com.ensah.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ElementPedagodique {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElementPedagogique;

	private String nom;
	
	
	
	 @JsonIgnore
	 @OneToMany(mappedBy = "elementPedagogique",cascade = CascadeType.ALL)
	   private List<Exam> examens;


	   @ManyToOne
	   @JoinColumn(name = "niveau_id")
	    private Niveau niveau;
	   
	   
	   
	   
	   @ManyToOne
	   @JoinColumn(name = "typeElement_id")
	    private TypeElement typeElement;
	   
	    @ManyToOne
	    @JoinColumn(name = "idcoordinator")
	    private Enseignant coordonnateur;
	    
	    @ManyToOne
	    @JsonIgnore
	    @JoinColumn(name = "idprofesseur")
	    private Enseignant professeur;

	    
		

}
