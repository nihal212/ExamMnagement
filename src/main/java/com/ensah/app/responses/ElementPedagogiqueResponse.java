package com.ensah.app.responses;

import com.ensah.app.entities.Enseignant;
import com.ensah.app.entities.Niveau;
import com.ensah.app.entities.TypeElement;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ElementPedagogiqueResponse {

	    private Long idElementPedagogique;
		@NotBlank(message = "This field is required")

	    private String nom;
		

	    private PersonResponse coordonnateur;
	    private int idprofesseur;
	    private int niveau_id;
	    private int typeElement_id;
	    private TypeElement typeElement;

         private Enseignant professeur;
		
	   // private List<ExamResponse> examens;
		
		
         private Niveau niveau;


}
