package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.app.entities.Personnel;
import com.ensah.app.repository.IPersonnelRepository;
import com.ensah.app.services.IPersonnelService;



@Service
@Transactional
public class PersonnelServiceImpl implements IPersonnelService {

	@Autowired
	private IPersonnelRepository personDao;

	public List<Personnel> getAllPersonnes() {

		return personDao.findAll();
	}

	public void deletePersonnel(Long id) {
		personDao.deleteById(id);

	}

	public Personnel getPersonnelById(Long id) {
		return personDao.findById(id).orElse(null);

	}

	public void addPersonnel(Personnel pPerson) {
		personDao.save(pPerson);

	}

	public void updatePersonnel(Personnel pPerson) {
		personDao.save(pPerson);

	}

	
	public Personnel getPersonnelByCin(String cin) {

		return personDao.getPersonnelByCin(cin);

	}

	@Override
	public boolean existsByCin(String cin) {
		// TODO Auto-generated method stub
		return personDao.existsByCin(cin);
	}

	

	

}
