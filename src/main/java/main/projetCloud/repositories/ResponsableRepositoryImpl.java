package main.projetCloud.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import main.projetCloud.domain.Admin;
import main.projetCloud.domain.Responsable;
import main.projetCloud.exceptions.EtAuthException;

@Repository

public class ResponsableRepositoryImpl implements ResponsableRepository{
	private static final String SQL_CREATE = "INSERT INTO RESPONSABLE(id,idregion,email,mdp)"
			+ "VALUES(nextval('responsable_id_seq'::regclass),?,?,?)";
	private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM RESPONSABLE WHERE email= ?"; 
	private static final String SQL_FIND_BY_ID = "SELECT * FROM RESPONSABLE WHERE id= ?";

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public Integer create(Integer idregion, String email, String mdp) {
		try {
			String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt(10));
			KeyHolder keyholder = new GeneratedKeyHolder();							
			jdbcTemplate.update(con ->{
				PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idregion);				
				ps.setString(2, email);				
				ps.setString(3, hashedPassword);
				return ps;
			} , keyholder);						
			return (Integer) keyholder.getKeys().get("id");
		}catch(Exception e) {
			throw new EtAuthException("Invalid details. Failed to create account");
		}		
	}

	@Override
	public Responsable find(String email, String mdp) {
		try {
			Responsable responsable = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] {email}, responsableRowMapper);
			if(!BCrypt.checkpw(mdp, responsable.getMdp())) throw new EtAuthException("Responsable Invalid name/password");
			return responsable;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid nom/password");
		}
	}

	@Override
	public Responsable findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id}, responsableRowMapper);
	}
	
	private RowMapper<Responsable> responsableRowMapper = ((rs, rowNum) -> {
		return new Responsable(
				rs.getInt("id"),	
				rs.getInt("idregion"),
				rs.getString("email"),
				rs.getString("mdp")
				);
	});

}
