package com.ryuseicode.siap.repository.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.admin.UserDataAdministrativeUnit;
import com.ryuseicode.siap.repository.admin.intf.IUserDataAdministrativeUnitRepository;
/**
 * @name UserDataAdministrativeUnitRepository
 * {@summary Repository class to implement the behavior of IUserDataAdministrativeUnitRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Repository
public class UserDataAdministrativeUnitRepository implements IUserDataAdministrativeUnitRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData AdministrativeUnit relation by ids}
	 * @param userDataId
	 * @param administrativeUnitId
	 * @return UserDataAdministrativeUnit
	 */
	public UserDataAdministrativeUnit GetByIds(int userDataId, int administrativeUnitId) {		
		List<UserDataAdministrativeUnit> result = jdbcTemplate.query(
                "select UA.* from UserData_AdministrativeUnit UA inner join UserData U on UA.UserDataId = u.userdataid inner join AdministrativeUnit A on UA.AdministrativeUnitId = A.AdministrativeUnitId  where U.Active = 1 and A.Active = 1 and U.userdataid = ? and A.administrativeunitid = ?",
                new Object[] { userDataId, administrativeUnitId },
                (rs, rowNum) ->
                        new UserDataAdministrativeUnit(
                        		rs.getInt("userdataid"),
                                rs.getInt("administrativeunitid")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByAdministrativeUnitId
	 * @param administrativeUnitId
	 * @return Collection of UserAdministrativeUnit
	 */
	public List<UserDataAdministrativeUnit> GetByAdministrativeUnitId(int administrativeUnitId) {
		return jdbcTemplate.query(
                "select UA.* from UserData_AdministrativeUnit UA inner join UserData U on UA.UserDataId = u.userdataid inner join AdministrativeUnit A on UA.AdministrativeUnitId = A.AdministrativeUnitId  where U.Active = 1 and A.Active = 1 and A.administrativeunitid = ?",
                new Object[] { administrativeUnitId },
                (rs, rowNum) ->
                        new UserDataAdministrativeUnit(
                        		rs.getInt("userdataid"),
                                rs.getInt("administrativeunitid")
                        )
        );
	}
	/**
	 * @name Save
	 * {@summary Method to save UserDataAdministrativeUnit}
	 * @param userDataRole
	 */
	public int Save(UserDataAdministrativeUnit userDataAdministrativeUnit) {
		return jdbcTemplate.update(
                "insert into UserData_AdministrativeUnit (userdataid, administrativeunitid) values(?,?)",
                userDataAdministrativeUnit.getUserDataId(), userDataAdministrativeUnit.getAdministrativeUnitId());
	}
	/**
	 * @name Delete
	 * {@summary Method to delete administrativeUnit  of user}
	 * @param userDataId
	 * @return
	 */
	public int Delete(int userDataId) {
		return jdbcTemplate.update(
                "delete from UserData_AdministrativeUnit where userdataid = ?",
                userDataId);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataAdministrativeUnit}
	 * @param userDataId
	 * @param administrativeUnitId
	 */
	public int Delete(int userDataId, int administrativeUnitId) {
		return jdbcTemplate.update(
                "delete from UserData_AdministrativeUnit where userdataid = ? and administrativeunitid = ?",
                userDataId, administrativeUnitId);
	}
}