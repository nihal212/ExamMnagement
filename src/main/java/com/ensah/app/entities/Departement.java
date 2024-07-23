package com.ensah.app.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter  @Setter
public class Departement {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idDepartement;
	    private String title;

	    @OneToMany(mappedBy = "departement")
	    private List<Enseignant> enseignants;

		public Long getIdDepartement() {
			return idDepartement;
		}

		public void setIdDepartement(Long idDepartement) {
			this.idDepartement = idDepartement;
		}

		public List<Enseignant> getEnseignants() {
			return enseignants;
		}

		public void setEnseignants(List<Enseignant> enseignants) {
			this.enseignants = enseignants;
		}

		
	    
}
