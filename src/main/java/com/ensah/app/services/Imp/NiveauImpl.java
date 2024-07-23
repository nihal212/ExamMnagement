package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.app.entities.Niveau;
import com.ensah.app.repository.INiveauRepository;
import com.ensah.app.services.INiveauService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NiveauImpl  implements INiveauService{
	
	@Autowired
	private INiveauRepository niveau;

	@Override
	public List<Niveau> getAllNiveau() {
		

		return niveau.findAll();
	}

	@Override
	public Niveau getNiveauById(Long n) {
		return niveau.findById(n).get();
	}
	

}
