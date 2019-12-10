package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.repository.award.intf.IAdjudicationDocumentRepository;

@Repository
public class AdjudicationDocumentRepository implements IAdjudicationDocumentRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of adjudication document }
	 * @param adjudicationId
	 * @return
	 */
	public List<AdjudicationDocument> GetByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from ADJUDICATIONDOCUMENT where adjudicationid = ? ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new AdjudicationDocument(
                        	rs.getInt("adjudicationdocumentid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("identifier"),
                        	rs.getString("name"),
                        	rs.getString("path")
                        )
        );
	}
	/**
	 * @name GetByName
	 * {@summary Method to get adjudication document by name }
	 * @param name
	 * @return
	 */
	public AdjudicationDocument GetByName(String name) {
		List<AdjudicationDocument> result = jdbcTemplate.query(
                "select * from ADJUDICATIONDOCUMENT where name = ? ",
                new Object[] { name },
                (rs, rowNum) ->
                        new AdjudicationDocument(
                        	rs.getInt("adjudicationdocumentid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("identifier"),
                        	rs.getString("name"),
                        	rs.getString("path")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save an adjudicationDocument }
	 * @param adjudicationDocument
	 * @return
	 */
	public int Save(AdjudicationDocument adjudicationDocument) {
		return jdbcTemplate.update(
                "insert into ADJUDICATIONDOCUMENT (adjudicationid, identifier, name, path) values(?,?,?,?)",
                adjudicationDocument.getAdjudicationId(), adjudicationDocument.getIdentifier(), adjudicationDocument.getName(), adjudicationDocument.getPath()
        );
	}
	
	/**
	 * @name Save
	 * {@summary Method to save a list of adjudicationDocuments}
	 * @param adjudicationDocuments
	 * @return
	 */
	public int Save(List<AdjudicationDocument> adjudicationDocuments) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(AdjudicationDocument adjudicationDocument : adjudicationDocuments) {
			Object[] row = new Object[4];
			row[0] =  adjudicationDocument.getAdjudicationId();
			row[1] =  adjudicationDocument.getIdentifier();
			row[2] =  adjudicationDocument.getName();
			row[3] =  adjudicationDocument.getPath();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into ADJUDICATIONDOCUMENT (adjudicationid, identifier, name, path) values(?,?,?,?)", rows)).sum();
	}
}