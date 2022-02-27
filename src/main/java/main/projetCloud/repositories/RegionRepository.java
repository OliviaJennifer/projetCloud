package main.projetCloud.repositories;

import java.util.List;

import main.projetCloud.domain.Region;

public interface RegionRepository {
	
	Region regionByNom(String nom); 
	
	List<Region> allRegion();
}
