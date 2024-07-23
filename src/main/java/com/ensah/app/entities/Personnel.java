package com.ensah.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @Data @NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
public class Personnel {
	 @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long idPersonnel;
	    @NotNull 
		@NotBlank(message = "This field is required")

	   private	String nom;
	   @NotNull
		@NotBlank(message = "This field is required")
	   
		@NotBlank(message = "This field is required")
       private  String  prenom;
	   
		@NotBlank(message = "This field is required")
		private String email;
	   
	   @Column(unique = true)
	   private String cin;

		private String photo;

  
  
  
   
}
