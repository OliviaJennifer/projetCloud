package main.projetCloud.repositories;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import main.projetCloud.domain.Admin;
import main.projetCloud.domain.Region;
import main.projetCloud.exceptions.EtAuthException;

@Repository
public class RegionRepositoryImpl implements RegionRepository{
	
	private static final String SQL_FIND_BY_NOM = "SELECT * FROM REGION WHERE nom= ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM REGION";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Region regionByNom(String nom) {
		try {
			Region region = jdbcTemplate.queryForObject(SQL_FIND_BY_NOM, new Object[] {nom}, regionRowMapper);			
			return region;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("name of the region not found");
		}
	} 
	private RowMapper<Region> regionRowMapper = ((rs, rowNum) -> {
		return new Region(rs.getInt("id"),							
				rs.getString("nom")
				);
	});

	@Override
	public List<Region> allRegion() {
		try {
			 List<Region> region = jdbcTemplate.query(SQL_FIND_ALL, new Object[] {}, regionRowMapper);		
			return region;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("name of the region not found");
		}
	}
}
