package com.ensah.app.services;

import com.ensah.app.entities.Groupe;
import java.util.List;

public interface IGroupeService {
	 Groupe findByIdGroup(long id);
	 List<Groupe> getAllGroup();

}
