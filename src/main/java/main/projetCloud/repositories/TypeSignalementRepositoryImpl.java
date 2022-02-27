package main.projetCloud.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import main.projetCloud.domain.Region;
import main.projetCloud.domain.TypeSignalement;
import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

@Repository
public class TypeSignalementRepositoryImpl implements TypeSignalementRepository{
	private static final String SQL_FIND_BY_DESC = "SELECT * FROM TYPESIGNALEMENT WHERE description= ?"; 
	private static final String SQL_FIND_BY_ID = "SELECT * FROM TYPESIGNALEMENT WHERE id= ?"; 
	private static final String SQL_FIND_ALL = "SELECT * FROM TYPESIGNALEMENT";

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public TypeSignalement typeSignalementBydesc(String description) {
		try {
			TypeSignalement typeSignalement = jdbcTemplate.queryForObject(SQL_FIND_BY_DESC, new Object[] {description}, typeSignalementRowMapper);
			return typeSignalement;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid type de signal");
		}
	}

	@Override
	public TypeSignalement typeSignalementById(Integer id) {
		try {
			TypeSignalement typeSignalement = jdbcTemplate.queryForObject(SQL_FIND_BY_DESC, new Object[] {id}, typeSignalementRowMapper);
			return typeSignalement;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid type de signal");
		}
	}
	@Override
	public List<TypeSignalement> allType() {
		try {
			 List<TypeSignalement> typeSignalement = jdbcTemplate.query(SQL_FIND_ALL, new Object[] {}, typeSignalementRowMapper);		
			return typeSignalement;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Type signalement not found");
		}
	}
	private RowMapper<TypeSignalement> typeSignalementRowMapper = ((rs, rowNum) -> {
		return new TypeSignalement(Integer.valueOf(
				rs.getInt("id")),				
				rs.getString("description"),
				rs.getString("couleur")
				);
	});

	

}
