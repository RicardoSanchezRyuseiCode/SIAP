package com.ryuseicode.siap.repository.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.admin.UserDataRole;
import com.ryuseicode.siap.repository.admin.intf.IUserDataRoleRepository;

@Repository
public class UserDataRoleRepository implements IUserDataRoleRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData Role relation by ids}
	 * @param userDataId
	 * @param roleId
	 * @return UserDataRol
	 */
	public UserDataRole GetByIds(int userDataId, int roleId) {
		List<UserDataRole> result = jdbcTemplate.query(
                "select UR.* from UserData_Role UR inner join UserData U on UR.UserDataId = u.userdataid inner join Role R on UR.RoleId = R.RoleId  where U.Active = 1 and R.Active = 1 and U.userdataid = ? and R.roleid = ?",
                new Object[] { userDataId, roleId },
                (rs, rowNum) ->
                        new UserDataRole(
                        		rs.getInt("userdataid"),
                                rs.getInt("roleid")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save UserDataRole}
	 * @param userDataRole
	 */
	public int Save(UserDataRole userDataRole) {
		return jdbcTemplate.update(
                "insert into UserData_Role (userdataid, roleid) values(?,?)",
                userDataRole.getUserDataId(), userDataRole.getRoleId());
	}
	/**
	 * @name Delete
	 * {@summary Method to delete roles of user}
	 * @param userDataId
	 */
	public int Delete(int userDataId) {
		return jdbcTemplate.update(
                "delete from UserData_Role where userdataid = ?",
                userDataId);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 * @param roleId
	 */
	public int Delete(int userDataId, int roleId) {
		return jdbcTemplate.update(
                "delete from UserData_Role where userdataid = ?, roleid = ?",
                userDataId, roleId);
	}
}
