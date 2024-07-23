package com.ensah.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity @Getter
@Setter
public class  TypeElement {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	  private String nom;
	  
	    @OneToMany(mappedBy = "typeElement") 
	    @JsonIgnore
	    private List<ElementPedagodique> elements;
		
}
