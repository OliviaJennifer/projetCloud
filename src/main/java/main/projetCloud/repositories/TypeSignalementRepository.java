package main.projetCloud.repositories;

import java.util.List;

import main.projetCloud.domain.TypeSignalement;

public interface TypeSignalementRepository {

	TypeSignalement typeSignalementBydesc(String description);
	
	TypeSignalement typeSignalementById(Integer id);
	
	List<TypeSignalement> allType();
		
}
