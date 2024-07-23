package com.ensah.app.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Surveillance {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSurveillance;
	
	
	
    @ManyToOne
    @JoinColumn(name = "idAdmin")
     private Administrateur administrateur ;
    
    @ManyToOne
    @JoinColumn(name = "idCordonnateur")
     private Enseignant coordonnateur ;
    
    @ManyToOne
    private Salle salle;
    
    @ManyToMany(mappedBy = "surveillances")
    private List<Enseignant> enseignants;
    @ManyToOne
    @JoinColumn(name = "id_Exam")
    private Exam exam;
	
    
    
    
}
