package com.ensah.app.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.app.entities.ElementPedagodique;
import com.ensah.app.entities.Personnel;
import com.ensah.app.repository.IElementPedagogiqueRepository;
import com.ensah.app.services.IElementPedogogiqueService;

@Service
@Transactional

public class ElementPedagogiqueImp implements IElementPedogogiqueService {
	
	@Autowired
	private IElementPedagogiqueRepository EP;

	@Override
	public void addElementPedagogique(ElementPedagodique e) {
		// TODO Auto-generated method stub
       EP.save(e);
}

	@Override
	public void updateElementPedagogique(ElementPedagodique e) {

       EP.save(e);		
	}

	@Override
	public List<ElementPedagodique> getAllElementPedagogique() {
		// TODO Auto-generated method stub
		return EP.findAll();
	}

	@Override
	public void deleteElementPedagogique(Long id) {
   EP.deleteById(id);		
	}

	@Override
	public ElementPedagodique getElementPedagogiqueById(Long id) {
		return EP.findById(id).get();
	}

	@Override
	public boolean existsBynom(String nom) {
		
		return EP.existsBynom(nom);
	}

	

}
