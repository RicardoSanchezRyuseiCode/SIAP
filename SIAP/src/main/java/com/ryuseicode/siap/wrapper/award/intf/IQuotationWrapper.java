package com.ryuseicode.siap.wrapper.award.intf;

import java.time.LocalDate;
import java.util.List;

import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.entity.award.QuotationDetail;
/**
 * @name IQuotationWrapper
 * {@summary Interface to define the behavior of  }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public interface IQuotationWrapper {
	/**
	 * @name create
	 * {@summary Method to create quotation  }
	 * @param quotation
	 * @param quotationDetails
	 * @return
	 */
	List<String> create(Quotation quotation, List<QuotationDetail> quotationDetails) throws Exception;
	/**
	 * @name ValidateQuotationDate
	 * {@summary Method to validate quotation date }
	 * @param adjudicationId
	 * @param quotationDate
	 * @return
	 * @throws Exception
	 */
	String validateQuotationDate(int adjudicationId, LocalDate quotationDate) throws Exception;
}
