package main.projetCloud.repositories;

import java.sql.Date;
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
import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

@Repository
public class AdminRepositoryImp implements AdminRepository{
	private static final String SQL_CREATE = "INSERT INTO ADMIN(id,email,mdp)"
			+ "VALUES(nextval('admin_id_seq'::regclass),?,?)";
	private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM ADMIN WHERE email= ?"; 
	private static final String SQL_FIND_BY_ID = "SELECT * FROM ADMIN WHERE id= ?";

	
	@Autowired
	JdbcTemplate jdbcTemplate;
 
	@Override
	public Integer create(String email, String mdp) {
		try {
			System.out.println("tonga tato : "+mdp+"//  "+email);
			String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt(10));
			KeyHolder keyholder = new GeneratedKeyHolder();							
			jdbcTemplate.update(con ->{
				PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, email);				
				ps.setString(2, hashedPassword);
				return ps;
			} , keyholder);						
			return (Integer) keyholder.getKeys().get("id");
		}catch(Exception e) {
			throw new EtAuthException("Invalid details. Failed to create account");
		}		
	}

	@Override
	public Admin find(String email, String mdp) {
		try {
			Admin admin = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] {email}, adminRowMapper);
			if(!BCrypt.checkpw(mdp, admin.getMdp())) throw new EtAuthException("Admin Invalid name/password");
			return admin;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("Invalid nom/password");
		}
	}
	
	@Override
	public Admin findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id}, adminRowMapper);
	}
	private RowMapper<Admin> adminRowMapper = ((rs, rowNum) -> {
		return new Admin(rs.getInt("id"),				
				rs.getString("email"),
				rs.getString("mdp")
				);
	});
	
}
