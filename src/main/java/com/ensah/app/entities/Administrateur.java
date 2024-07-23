package com.ensah.app.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "idAdministrateur")
public class Administrateur extends Personnel {
	 @NotNull 
	@NotBlank(message = "This field is required")

		private String profession;


    @OneToMany(mappedBy = "administrateur")
    private List<Surveillance> surveillances;


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public List<Surveillance> getSurveillances() {
		return surveillances;
	}


	public void setSurveillances(List<Surveillance> surveillances) {
		this.surveillances = surveillances;
	} 
    



}
