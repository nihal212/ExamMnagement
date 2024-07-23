package com.ensah.app.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
public class PersonnelRequest {
	 private String nom;
	    private String prenom;
	    private String cin;
	    private String specialite;
	    private String profession;
	    private int typePerson;
	    private String email;
}