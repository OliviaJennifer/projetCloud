package main.projetCloud.repositories;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

@Repository
public class UserRepositoryImpl implements UserRepository{

	private static final String SQL_CREATE = "INSERT INTO UTILISATEUR(id,nom,prenom,sexe,dtn,adresse,email,password)"
			+ "VALUES(nextval('utilisateur_id_seq'::regclass),?,?,?,?,?,?,?)";
	private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM UTILISATEUR WHERE EMAIL= ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM UTILISATEUR WHERE id= ?";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM UTILISATEUR WHERE nom= ?"; 
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer create(String nom, String prenom, String sexe, String dtn, String adresse, String email,
			String password) throws EtAuthException {
		try {
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
			KeyHolder keyholder = new GeneratedKeyHolder();							
			jdbcTemplate.update(con ->{
				PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ps.setString(3, sexe);
				ps.setDate(4, Date.valueOf(dtn) );
				ps.setString(5, adresse);
				ps.setString(6, email);
				ps.setString(7, hashedPassword);
				return ps;
			} , keyholder);						
			return (Integer) keyholder.getKeys().get("id");
		}catch(Exception e) {
			throw new EtAuthException("Invalid details. Failed to create account");
		}		
	}

	@Override
	public User findByNamePassword(String name, String password) throws EtAuthException {
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, new Object[] {name}, userRowMapper);
			if(!BCrypt.checkpw(password, user.getPassword())) throw new EtAuthException("Invalid name/password");
			return user;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid nom/password");
		}
	}
	
	@Override
	public User findByName(String name)throws EtAuthException{
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, new Object[] {name}, userRowMapper);
			return user;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid nom/password");
		}
	}
	
	@Override
	public Integer getCountByEmail(String email) {
		return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] {email}, Integer.class); 
	}

	@Override
	public User findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id}, userRowMapper);
	}
	
	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("id"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getString("sexe"),
				rs.getString("dtn"),
				rs.getString("adresse"),
				rs.getString("email"),
				rs.getString("password")
				);
	});

}
