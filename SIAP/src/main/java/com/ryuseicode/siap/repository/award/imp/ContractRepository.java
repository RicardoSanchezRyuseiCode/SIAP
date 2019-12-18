package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Contract;
import com.ryuseicode.siap.repository.award.intf.IContractRepository;
/**
 * @name ContractRepository
 * {@summary Repository class to implement IContractRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
@Repository
public class ContractRepository implements IContractRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of contracts by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public List<Contract> getByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from contract where adjudicationid = ? ",
                new Object[] { adjudicationId },
                (rs, rowNum) -> new Contract(
            		rs.getInt("contractid"),
            		rs.getInt("competitorid"),
            		rs.getInt("adjudicationid"),
            		rs.getString("taxid"),
            		rs.getString("sourceresource"),
            		rs.getString("numbermatch"),
            		rs.getString("email"),
            		rs.getDouble("maxamount"),
            		rs.getString("maxamounttext"),
            		rs.getDouble("minamount"),
            		rs.getString("minamounttext"),
            		rs.getDouble("maxamountiva"),
            		rs.getString("maxamountivatext"),
            		rs.getDouble("minamountiva"),
            		rs.getString("minamountivatext"),
            		rs.getInt("physicalperson"),
            		rs.getString("physicalpersondata"),
            		rs.getString("legaldeedNumber"),
            		rs.getString("legalnotarynumber"),
            		rs.getString("legalstate"),
            		rs.getString("legalcity"),
            		rs.getDate("legaldate") != null ? new Timestamp(rs.getDate("legaldate").getTime()).toLocalDateTime().toLocalDate() : null,
            		rs.getString("legaldatetext"),
            		rs.getString("legalagentdeednumber"),
            		rs.getString("legalagentnotarynumber"),
            		rs.getString("legalagentstate"),
            		rs.getString("legalagentcity"),
            		rs.getDate("legalagentdate") != null ? new Timestamp(rs.getDate("legalagentdate").getTime()).toLocalDateTime().toLocalDate() : null,
            		rs.getString("legalagentdatetext"),
            		rs.getDouble("deposit"),
            		rs.getString("deposittext"),
            		rs.getDouble("depositpercent"),
            		rs.getString("depositpercenttext"),
            		rs.getDouble("depositinadvance"),
            		rs.getString("depositinadvancetext"),
            		new Timestamp(rs.getDate("duedate").getTime()).toLocalDateTime().toLocalDate(),
            		rs.getString("duedatetext"),
            		rs.getString("winnername")
        		)	
        );
	}
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get contract by competitorId }
	 * @param competitorId
	 * @return
	 */
	public Contract getByCompetitorId(int competitorId) {
		List<Contract> result = jdbcTemplate.query(
                "select * from contract where competitorid = ? ",
                new Object[] { competitorId },
                (rs, rowNum) -> new Contract(
                		rs.getInt("contractid"),
                		rs.getInt("competitorid"),
                		rs.getInt("adjudicationid"),
                		rs.getString("taxid"),
                		rs.getString("sourceresource"),
                		rs.getString("numbermatch"),
                		rs.getString("email"),
                		rs.getDouble("maxamount"),
                		rs.getString("maxamounttext"),
                		rs.getDouble("minamount"),
                		rs.getString("minamounttext"),
                		rs.getDouble("maxamountiva"),
                		rs.getString("maxamountivatext"),
                		rs.getDouble("minamountiva"),
                		rs.getString("minamountivatext"),
                		rs.getInt("physicalperson"),
                		rs.getString("physicalpersondata"),
                		rs.getString("legaldeedNumber"),
                		rs.getString("legalnotarynumber"),
                		rs.getString("legalstate"),
                		rs.getString("legalcity"),
                		rs.getDate("legaldate") != null ? new Timestamp(rs.getDate("legaldate").getTime()).toLocalDateTime().toLocalDate() : null,
                		rs.getString("legaldatetext"),
                		rs.getString("legalagentdeednumber"),
                		rs.getString("legalagentnotarynumber"),
                		rs.getString("legalagentstate"),
                		rs.getString("legalagentcity"),
                		rs.getDate("legalagentdate") != null ? new Timestamp(rs.getDate("legalagentdate").getTime()).toLocalDateTime().toLocalDate() : null,
                		rs.getString("legalagentdatetext"),
                		rs.getDouble("deposit"),
                		rs.getString("deposittext"),
                		rs.getDouble("depositpercent"),
                		rs.getString("depositpercenttext"),
                		rs.getDouble("depositinadvance"),
                		rs.getString("depositinadvancetext"),
                		new Timestamp(rs.getDate("duedate").getTime()).toLocalDateTime().toLocalDate(),
                		rs.getString("duedatetext"),
                		rs.getString("winnername")
        		)	
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name getByContractId
	 * {@summary Method to get a contract by contract id }
	 * @param contractId
	 * @return
	 */
	public Contract getByContractId(int contractId) {
		List<Contract> result = jdbcTemplate.query(
                "select * from contract where contractid = ? ",
                new Object[] { contractId },
                (rs, rowNum) -> new Contract(
                		rs.getInt("contractid"),
                		rs.getInt("competitorid"),
                		rs.getInt("adjudicationid"),
                		rs.getString("taxid"),
                		rs.getString("sourceresource"),
                		rs.getString("numbermatch"),
                		rs.getString("email"),
                		rs.getDouble("maxamount"),
                		rs.getString("maxamounttext"),
                		rs.getDouble("minamount"),
                		rs.getString("minamounttext"),
                		rs.getDouble("maxamountiva"),
                		rs.getString("maxamountivatext"),
                		rs.getDouble("minamountiva"),
                		rs.getString("minamountivatext"),
                		rs.getInt("physicalperson"),
                		rs.getString("physicalpersondata"),
                		rs.getString("legaldeedNumber"),
                		rs.getString("legalnotarynumber"),
                		rs.getString("legalstate"),
                		rs.getString("legalcity"),
                		rs.getDate("legaldate") != null ? new Timestamp(rs.getDate("legaldate").getTime()).toLocalDateTime().toLocalDate() : null,
                		rs.getString("legaldatetext"),
                		rs.getString("legalagentdeednumber"),
                		rs.getString("legalagentnotarynumber"),
                		rs.getString("legalagentstate"),
                		rs.getString("legalagentcity"),
                		rs.getDate("legalagentdate") != null ? new Timestamp(rs.getDate("legalagentdate").getTime()).toLocalDateTime().toLocalDate() : null,
                		rs.getString("legalagentdatetext"),
                		rs.getDouble("deposit"),
                		rs.getString("deposittext"),
                		rs.getDouble("depositpercent"),
                		rs.getString("depositpercenttext"),
                		rs.getDouble("depositinadvance"),
                		rs.getString("depositinadvancetext"),
                		new Timestamp(rs.getDate("duedate").getTime()).toLocalDateTime().toLocalDate(),
                		rs.getString("duedatetext"),
                		rs.getString("winnername")
        		)	
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name save
	 * {@summary Method to save a contract }
	 * @param contract
	 * @return
	 */
	public int save(Contract contract) {
		return jdbcTemplate.update(
                "insert into contract (COMPETITORID, ADJUDICATIONID, TAXID, SOURCERESOURCE, NUMBERMATCH, EMAIL, MAXAMOUNT, MAXAMOUNTTEXT ,MINAMOUNT, MINAMOUNTTEXT, maxamountiva, maxamountivatext, minamountiva, minamountivatext ,PHYSICALPERSON, PHYSICALPERSONDATA, LEGALDEEDNUMBER, LEGALNOTARYNUMBER, LEGALSTATE, LEGALCITY, LEGALDATE, LEGALDATETEXT, LEGALAGENTDEEDNUMBER, LEGALAGENTNOTARYNUMBER, LEGALAGENTSTATE, LEGALAGENTCITY, LEGALAGENTDATE, LEGALAGENTDATETEXT, DEPOSIT, deposittext, depositpercent, depositpercenttext, depositinadvance, depositinadvancetext, DUEDATE, DUEDATETEXT, WINNERNAME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                contract.getCompetitorId(), contract.getAdjudicationId(), contract.getTaxId(), contract.getSourceResource(), contract.getNumberMatch(), contract.getEmail(), contract.getMaxAmount(), contract.getMaxAmountText(), contract.getMinAmount(), contract.getMinAmountText(),
                contract.getMaxAmountIva(), contract.getMaxAmountIvaText(), contract.getMinAmountIva(), contract.getMinAmountIvaText(), contract.getPhysicalPerson(), contract.getPhysicalPersonData(), contract.getLegalDeedNumber(), contract.getLegalNotaryNumber(), contract.getLegalState(), contract.getLegalCity(), contract.getLegalDate(), contract.getLegalDateText(),  
                contract.getLegalAgentDeedNumber(), contract.getLegalAgentNotaryNumber(), contract.getLegalAgentState(), contract.getLegalAgentCity(), contract.getLegalAgentDate(), contract.getLegalAgentDateText(), contract.getDeposit(), contract.getDepositText(), contract.getDepositPercent(), contract.getDepositPercentText(), contract.getDepositInAdvance(), 
                contract.getDepositInAdvanceText(),contract.getDueDate(), contract.getDueDateText(), contract.getWinnerName()
        );
	}
}
