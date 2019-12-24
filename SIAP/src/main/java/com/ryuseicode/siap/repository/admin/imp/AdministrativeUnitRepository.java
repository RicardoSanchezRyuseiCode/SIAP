package com.ryuseicode.siap.repository.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;
import com.ryuseicode.siap.repository.admin.intf.IAdministrativeUnitRepository;

/**
 * @name AdministrativeUnitRepository
 * {@summary Class to implement the behavior of AdministrativeUnitRepository}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25 
 */
@Repository
public class AdministrativeUnitRepository implements IAdministrativeUnitRepository {

	/**
	 * jdbcTemplate
	 */
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * @name Get
	 * {@summary Method to get a collection of AdministrativeUnit}
	 * @return collection of AdministrativeUnit
	 */
	public List<AdministrativeUnit> Get() {
		return jdbcTemplate.query(
                "select * from AdministrativeUnit where Active = 1 ",
                (rs, rowNum) ->
                        new AdministrativeUnit(
                        		rs.getInt("administrativeunitid"),
                                rs.getString("code"),
                                rs.getString("description"),
                                rs.getString("email"),
                                rs.getInt("authorizer"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByAdministrativeUnitId
	 * {@summary Method to get userData by id}
	 * @param userDataId
	 * @return
	 */
	public AdministrativeUnit GetByAdministrativeUnitId(int administrativeUnitId) {
		List<AdministrativeUnit> result = jdbcTemplate.query(
				 "select * from AdministrativeUnit where administrativeunitid = ? and Active = 1 ",
	                new Object[]{ administrativeUnitId },
	                (rs, rowNum) ->
	                        new AdministrativeUnit(
	                        		rs.getInt("administrativeunitid"),
	                                rs.getString("code"),
	                                rs.getString("description"),
	                                rs.getString("email"),
	                                rs.getInt("authorizer"),
	                                rs.getInt("active")
	                        )
	        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByCode
	 * {@summary Method to get a userData by code}
	 * @param nickname
	 * @return
	 */
	public AdministrativeUnit GetByCode(String code) {
		List<AdministrativeUnit> result = jdbcTemplate.query(
				 "select * from AdministrativeUnit where LOWER(code) = LOWER(?) and Active = 1 ",
	                new Object[]{ code },
	                (rs, rowNum) ->
	                        new AdministrativeUnit(
	                        		rs.getInt("administrativeunitid"),
	                                rs.getString("code"),
	                                rs.getString("description"),
	                                rs.getString("email"),
	                                rs.getInt("authorizer"),
	                                rs.getInt("active")
	                        )
	        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByCodeDifferentId
	 * @param code
	 * @param administrativeUnitId
	 * @return
	 */
	public AdministrativeUnit GetByCodeDifferentId(String code, int administrativeUnitId) {
		List<AdministrativeUnit> result = jdbcTemplate.query(
				 "select * from AdministrativeUnit where LOWER(code) = LOWER(?) and administrativeunitid <> ? and Active = 1 ",
	                new Object[]{ code, administrativeUnitId },
	                (rs, rowNum) ->
	                        new AdministrativeUnit(
	                        		rs.getInt("administrativeunitid"),
	                                rs.getString("code"),
	                                rs.getString("description"),
	                                rs.getString("email"),
	                                rs.getInt("authorizer"),
	                                rs.getInt("active")
	                        )
	        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByAuthorizer
	 * @return
	 */
	public AdministrativeUnit GetByAuthorizer() {
		List<AdministrativeUnit> result = jdbcTemplate.query(
				 "select * from AdministrativeUnit where authorizer = 1 and Active = 1 ",
	                (rs, rowNum) ->
	                        new AdministrativeUnit(
	                        		rs.getInt("administrativeunitid"),
	                                rs.getString("code"),
	                                rs.getString("description"),
	                                rs.getString("email"),
	                                rs.getInt("authorizer"),
	                                rs.getInt("active")
	                        )
	        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByUserId
	 * {@summary Method to get by userId }
	 * @param userId
	 * @return
	 */
	public AdministrativeUnit GetByUserDataId(int userDataId) {
		List<AdministrativeUnit> result = jdbcTemplate.query(
				 "select a.* from administrativeunit a inner join userdata_administrativeunit ua on a.administrativeunitid = ua.administrativeunitid  where userdataid = ? and active = 1",
				 new Object[] { userDataId },
	                (rs, rowNum) ->
	                        new AdministrativeUnit(
	                        		rs.getInt("administrativeunitid"),
	                                rs.getString("code"),
	                                rs.getString("description"),
	                                rs.getString("email"),
	                                rs.getInt("authorizer"),
	                                rs.getInt("active")
	                        )
	        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name Save
	 * @param AdministrativeUnit
	 */
	public int Save(AdministrativeUnit administrativeUnit) {
		return jdbcTemplate.update(
                "insert into AdministrativeUnit (code, description, email, authorizer, active) values(?,?,?,?,?)",
                administrativeUnit.getCode() ,administrativeUnit.getDescription(),administrativeUnit.getEmail(), administrativeUnit.getAuthorizer(), administrativeUnit.getActive());
	}
	/**
	 * @name Update
	 * @param AdministrativeUnit
	 */
	public int Update(AdministrativeUnit administrativeUnit) {
		return jdbcTemplate.update(
                "update AdministrativeUnit set code = ?, description = ?, email = ?, authorizer = ? where administrativeunitid = ?",
                administrativeUnit.getCode() ,administrativeUnit.getDescription() ,administrativeUnit.getEmail(), administrativeUnit.getAuthorizer(), administrativeUnit.getAdministrativeUnitId());
	}
	/**
	 * @name Delete
	 * @param administrativeUnitId
	 */
	public int Delete(int administrativeUnitId) {
		return jdbcTemplate.update(
                "delete from AdministrativeUnit where administrativeunitid = ?",
                administrativeUnitId);
	}
}
