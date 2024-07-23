package com.ensah.app.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter 
public class PersonResponse {

	
	public static final int TYPE_PROF = 1;
	public static final int TYPE_Administrateur = 2;
	private String photo;

   private String specialite;

	//@NotBlank(message = "This field is required")
    private String profession;

	private int typePerson;
	
   private int id_departement;
   private int id_group;

	
	private Long idPersonnel;

	@NotBlank(message = "This field is required")
	private String nom;

	@NotBlank(message = "This field is required")
	private String prenom;
	
	@NotBlank(message = "This field is required")
	private String cin;

	@Email(message = "Email should be valid")
	@NotBlank(message = "This field is required")
	private String email;
	public PersonResponse(int typePerson) {
		this.typePerson = typePerson;
	}
	public PersonResponse() {
		
	}
	private Object roles;

	public static int getTypeProf() {
		return TYPE_PROF;
	}

	public static int getTypeAdministrteur() {
		return TYPE_Administrateur;
	}


}
