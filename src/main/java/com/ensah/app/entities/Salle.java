package com.ensah.app.entities;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRoom;
    private Long size;
    // Autres attributs

    @OneToMany(mappedBy = "salle")
    private List<Surveillance> surveillances = new ArrayList<>();

	

	
    

}