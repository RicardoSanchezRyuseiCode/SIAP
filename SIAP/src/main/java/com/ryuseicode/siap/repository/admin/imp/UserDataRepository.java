package com.ryuseicode.siap.repository.admin.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.admin.UserData;
import com.ryuseicode.siap.paramoutput.admin.UserDataParamOutput;
import com.ryuseicode.siap.repository.admin.intf.IUserDataRepository;
/**
 * @name UserDataRepository
 * {@summary Class to implement the behavior of IUserDataRepository}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
@Repository
public class UserDataRepository implements IUserDataRepository {
	/**
	 * jdbcTemplate
	 */
	@Autowired
    private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data objects}
	 */
	public List<UserDataParamOutput> Get() {
		return jdbcTemplate.query(
                "select U.*,R.*, A.* from UserData_Role UR inner join UserData U on UR.UserDataId = u.userdataid inner join Role R on UR.RoleId = R.RoleId  inner join UserData_AdministrativeUnit UA on U.UserDataId = UA.UserDataId inner join AdministrativeUnit A on UA.AdministrativeUnitId = A.AdministrativeUnitId  where U.Active = 1 and R.Active = 1 and A.Active = 1",
                (rs, rowNum) ->
                        new UserDataParamOutput(
                        		new UserData(
	                        		rs.getInt(1),
	                                rs.getString(2),
	                                rs.getString(3),
	                                rs.getString(4),
	                                rs.getInt(5)
                                ),
                                rs.getInt(6),
                                rs.getInt(10)
                            )
                        );
	}
	/**
	 * @name GetByUserDataId
	 * {@summary Method to get userData by id}
	 * @param userDataId
	 * @return
	 */
	public UserDataParamOutput GetByUserDataId(int userDataId) {
		List<UserDataParamOutput> results = jdbcTemplate.query(
				 "select U.*,R.*, A.* from UserData_Role UR inner join UserData U on UR.UserDataId = u.userdataid inner join Role R on UR.RoleId = R.RoleId  inner join UserData_AdministrativeUnit UA on U.UserDataId = UA.UserDataId inner join AdministrativeUnit A on UA.AdministrativeUnitId = A.AdministrativeUnitId  where U.Active = 1 and R.Active = 1 and A.Active = 1 and U.userDataId = ? ",
	                new Object[]{ userDataId },
	                (rs, rowNum) ->
                		new UserDataParamOutput(
                    		new UserData(
                        		rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getInt(5)
                            ),
                            rs.getInt(6),
                            rs.getInt(10)
                        )   
	        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name GetByRowId
	 * {@summary Method to get user data by rowid }
	 * @param rowId
	 * @return
	 */
	public UserData GetByRowId(String rowId) {
		List<UserData> results = jdbcTemplate.query(
				 "select U.* from UserData U where U.Active = 1 and U.rowid = ? ",
	                new Object[]{ rowId },
	                (rs, rowNum) ->
                		
                		new UserData(
                    		rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5)
                        )
                           
	        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name GetByNickName
	 * @param nickname
	 * @return
	 */
	public UserDataParamOutput GetByNickname(String nickname) {
		 List<UserDataParamOutput> results =  jdbcTemplate.query(
				 "select U.*,R.*, A.* from UserData_Role UR inner join UserData U on UR.UserDataId = u.userdataid inner join Role R on UR.RoleId = R.RoleId  inner join UserData_AdministrativeUnit UA on U.UserDataId = UA.UserDataId inner join AdministrativeUnit A on UA.AdministrativeUnitId = A.AdministrativeUnitId  where U.Active = 1 and R.Active = 1 and A.Active = 1 and U.nickname = ? ",
				    new Object[]{ nickname },
	                (rs, rowNum) ->	                        
                		new UserDataParamOutput(
                    		new UserData(
	                        		rs.getInt(1),
	                                rs.getString(2),
	                                rs.getString(3),
	                                rs.getString(4),
	                                rs.getInt(5)
                                ),
                                rs.getInt(6),
                                rs.getInt(10)
        				)	
	        );
		 return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name Save
	 * @param userData
	 */
	public String Save(UserData userData) {
		KeyHolder holder = new GeneratedKeyHolder();		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into UserData (nickname, name, password, active) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, userData.getNickname());
				ps.setString(2, userData.getName());
				ps.setString(3, userData.getPassword());
				ps.setInt(4, userData.getActive());
				return ps;
			}
		}, holder);		
		Map<String, Object> keys = holder.getKeys();
  		String rowId = keys.get("rowid").toString();
		return rowId;
	}
	/**
	 * @name Update
	 * @param userData
	 */
	public int Update(UserData userData) {
		return jdbcTemplate.update(
                "update UserData set nickname = ?, name = ? where userdataid = ? ",
                userData.getNickname(),userData.getName(), userData.getUserDataId());
	}
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password}
	 * @param userData
	 * @return
	 */
	public int UpdatePassword(UserData userData) {
		return jdbcTemplate.update(
                "update UserData set password = ? where userdataid = ? ",
                userData.getPassword(), userData.getUserDataId());
	}
	/**
	 * @name Delete
	 * @param userDataId
	 */
	public int Delete(int userDataId) {
		return jdbcTemplate.update(
                "update UserData set active = ? where userdataid = ? ",
                0, userDataId);
	}	
}
