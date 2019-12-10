package com.ryuseicode.siap.wrapper.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;

/**
 * @name IAnnexWrapper
 * {@summary Interface to define the behavior of IAnnexWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAnnexWrapper {
	/**
	 * @name Create
	 * {@summary Method to create a list of annex and generate the document }
	 * @param adjudicationId
	 * @param annexs
	 */
	String create(int adjudicationId, List<Annex> annexs) throws Exception;
	/**
	 * @name Import
	 * {@summary Method to import an excel document an get a collection of AnnexParamOutput}
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	List<AnnexParamOutput> importFile(String filePath) throws Exception;
}
