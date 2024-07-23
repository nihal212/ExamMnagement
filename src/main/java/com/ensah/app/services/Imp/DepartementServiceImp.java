package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.app.entities.Departement;
import com.ensah.app.repository.IdepartementRepository;
import com.ensah.app.services.DepartementService;


@Service
@Transactional
public class DepartementServiceImp implements DepartementService{
	@Autowired
	private IdepartementRepository departement;
	

	@Override
	public List<Departement> getAllDepartements() {
		
		return departement.findAll();
	}

	@Override
	public Departement findByIdDeparetement(long n) {
	    return departement.findById(n).orElse(null);
	}
	
	

}
