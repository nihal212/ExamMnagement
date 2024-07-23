package com.ensah.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
@PrimaryKeyJoinColumn(name = "idEnseignent")
public class Enseignant  extends Personnel{
	 @NotNull 
		@NotBlank(message = "This field is required")
	 private String specialite;
	 

	  @OneToMany(mappedBy = "coordonnateur")
	    private List<Surveillance> surveillances1 ;

	 
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinTable(
	      name = "nom_enseignant_survellance",
	      joinColumns = @JoinColumn(name = "id_entite_enseignant"),
	      inverseJoinColumns = @JoinColumn(name = "id_entite_survellance")
	  )
	  private List<Surveillance> surveillances;

	  
	 
	    @ManyToOne
	    @JoinColumn(name="id_departement")
	    @JsonIgnore

	    private Departement departement;
	    
	    @OneToMany(mappedBy = "professeur")
	    @JsonIgnore

	    private List<ElementPedagodique> elements;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "coordonnateur")
	    private List<ElementPedagodique> elements1;

	   
	    
	    
	    
	    @ManyToOne
	    @JoinColumn(name="id_groupe")
	    private Groupe groupe;

	   

	
	    

}
