package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.repository.award.intf.IInstitutionRepository;
/**
 * @name InstitutionRepository
 * {@summary Repository to implement the behavior of IInstitutionRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
@Repository
public class InstitutionRepository implements IInstitutionRepository {
	/**
	 * jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summary Method to get a list of Institution objects}
	 * @return
	 */
	public List<Institution> get() {
		return jdbcTemplate.query(
                "select * from INSTITUTION where active = 1 ",
                (rs, rowNum) ->
                        new Institution(
                        		rs.getInt("institutionid"),
                        		rs.getString("name"),
                        		rs.getString("address"),
                        		rs.getString("representative"),
                        		rs.getString("accreditationnumber"),
                        		rs.getString("taxid"),
                        		rs.getString("designation"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getInt("active")
                        )
        );
	}
}
