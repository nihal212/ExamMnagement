package com.ensah.app.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public enum Session {
   Normal,Rattrapage;
   @Id
   private Long id;
}

