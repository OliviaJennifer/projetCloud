package main.projetCloud.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import main.projetCloud.domain.Region;
import main.projetCloud.domain.Signalement;
import main.projetCloud.domain.TypeSignalement;
import main.projetCloud.domain.User;
import main.projetCloud.exceptions.EtAuthException;

@Repository
public class SignalementRepositoryImpl implements SignalementRepository{

	private static final String SQL_CREATE = "INSERT INTO SIGNALEMENT(id, idutilisateur, idregion,idtypesignalement, daty,latitude, longitude, statut, commentaire,isaffecte)"
			+ "VALUES(nextval('signalement_id_seq'::regclass),?,?,?,now(),?,?,?,?,?)";
	private static final String SQL_FIND_ALL = "SELECT * FROM SIGNALEMENT"; 
	private static final String SQL_FIND_ALL_USER = "SELECT * FROM SIGNALEMENT where idutilisateur=?"; 

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	TypeSignalementRepository typeSignalementRepository; 

	@Override
	public Integer createSignalement(String usernom, String regionname, String type, Double longitude,
			Double latitude, String statu, String commentaire, String isAffecte) throws EtAuthException{
		
		User user = userRepository.findByName(usernom);
		Region region = regionRepository.regionByNom(regionname);
		TypeSignalement typesign = typeSignalementRepository.typeSignalementBydesc(type);
		System.out.println("CreateSignalement user: "+user.getEmail());
		System.out.println("CreateSignalement region: "+region.getNom());
		System.out.println("CreateSignalement type: "+typesign.getCouleur());
		try {
				KeyHolder keyholder = new GeneratedKeyHolder();							
				jdbcTemplate.update(con ->{
					PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, user.getId());
					ps.setInt(2, region.getId());
					ps.setInt(3, typesign.getId());
					ps.setDouble(4, latitude);
					ps.setDouble(5, longitude);
					ps.setString(6, statu);
					ps.setString(7, commentaire);
					ps.setString(8, isAffecte);
					return ps;
				} , keyholder);				
		
				return (Integer) keyholder.getKeys().get("id");
		}catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public List<Signalement> allSignalement() {
		try {
			 List<Signalement> signalement = jdbcTemplate.query(SQL_FIND_ALL, new Object[] {}, signalementRowMapper);		
			return signalement;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("signalement not found");
		}
	}
	@Override
	public List<Signalement> userSignalements(Integer iduser) {
		try {
			 List<Signalement> signalement = jdbcTemplate.query(SQL_FIND_ALL_USER, new Object[] {iduser}, signalementRowMapper);		
			return signalement;
		}catch(EmptyResultDataAccessException e) {
			throw new EtAuthException("signalement not found");
		}
	}
	private RowMapper<Signalement> signalementRowMapper = ((rs, rowNum) -> {
		return new Signalement(
				rs.getInt("id"),							
				rs.getInt("idRegion"),
				rs.getInt("idtypeSignalement"),
				rs.getInt("idUtilisateur"),
				rs.getDouble("longitude"),
				rs.getDouble("latitude"),
				rs.getString("commentaire"),
				rs.getDate("daty"),
				rs.getString("statut"),
				rs.getString("isAffecte")
				);
		//Integer id, Integer idRegion, Integer idTypeSignalement, Integer idUtilisateur, Double longitude, Double latitude, String commentaire, Date daty, String statut, String isAffecte)
	});


	
}