package com.ensah.app.services;

import java.util.List;

import com.ensah.app.entities.Departement;


public interface DepartementService {
	public List<Departement> getAllDepartements();
	public Departement findByIdDeparetement(long n);

}
