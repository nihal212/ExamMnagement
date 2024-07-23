package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.app.entities.Groupe;
import com.ensah.app.repository.IGroupeRepository;
import com.ensah.app.services.IGroupeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupeServiceImp implements IGroupeService {
    
    @Autowired
    private IGroupeRepository groupeRepository;
    
    @Override
    public Groupe findByIdGroup(long id) {
        return groupeRepository.findById(id).orElse(null);
    }

	@Override
	public List<Groupe> getAllGroup() {
		// TODO Auto-generated method stub
		return groupeRepository.findAll();
	}
}