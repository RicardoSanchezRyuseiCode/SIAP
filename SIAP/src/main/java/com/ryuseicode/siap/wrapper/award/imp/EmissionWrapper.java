package com.ryuseicode.siap.wrapper.award.imp;

import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.wrapper.award.intf.IEmissionWrapper;

@Service
public class EmissionWrapper implements IEmissionWrapper {

	/**
	 * @name ValidateDays
	 * {@summary Method to validate days }
	 * @param sourceOrigin
	 * @param adjudicationId
	 * @return message of validation
	 */
	public String ValidateDays(String sourceOrigin, int daysForPayment) throws Exception {
		if (sourceOrigin.equals(Adjudication.SOURCE_FEDERAL) || sourceOrigin.equals(Adjudication.SOURCE_MIXED)){
            if (daysForPayment < 1 || daysForPayment > 20){
                return "Con fundamento en el artículo 51 de la ley de adquisiciones, arrendamientos y servicios del sector público el termino máximo a señalarse es de 20 días.";
            }
        }
        else if (sourceOrigin.equals(Adjudication.SOURCE_STATE)){
            if (daysForPayment < 1 || daysForPayment > 45){
                return "Con fundamento en el artículo 115 de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal el termino máximo a señalarse es de 45 días";
            }
        }
		return "";
	}
}
