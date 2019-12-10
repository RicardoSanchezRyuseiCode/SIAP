package com.ryuseicode.siap.repository.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.DocumentVariable;
import com.ryuseicode.siap.repository.award.intf.IDocumentVariableRepository;
/**
 * @name DocumentVariableRepository
 * {@summary Repository class to implement the behavior of IDocumentVariableRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
@Repository
public class DocumentVariableRepository implements IDocumentVariableRepository {
	/**
	 * jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summary Method to get a list of document variable }
	 * @return
	 */
	public List<DocumentVariable> Get() { 
		return jdbcTemplate.query(
                "select * from DOCUMENTVARIABLE where active = 1 ",
                (rs, rowNum) ->
                        new DocumentVariable(
                        		rs.getInt("documentvariableid"),
                        		rs.getString("variable"),
                        		rs.getString("classname"),
                        		rs.getString("methodname"),
                        		rs.getString("returntype"),
                                rs.getInt("active")
                        )
        );
	}
}
