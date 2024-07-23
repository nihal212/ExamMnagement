package com.ensah.app.services;

import java.util.List;

import com.ensah.app.entities.Niveau;


public interface INiveauService {
	List<Niveau> getAllNiveau();
	public Niveau getNiveauById(Long n);
	

}
