package com.ensah.app.services;

import java.util.List;

import com.ensah.app.entities.Personnel;


public interface IPersonnelService {

	public void addPersonnel(Personnel pPerson);

	public void updatePersonnel(Personnel pPerson);

	public List<Personnel> getAllPersonnes();

	public void deletePersonnel(Long id);

	public Personnel getPersonnelById(Long id);
    boolean existsByCin(String cin);

	public Personnel getPersonnelByCin(String cin);

	
	
	
	

}
