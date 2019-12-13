package com.ryuseicode.siap.wrapper.award.imp;

import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.wrapper.award.intf.IAdjudicationWrapper;
/**
 * @name AdjudicationWrapper
 * {@summary Wrapper class to implement the behavior }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 2, 2019
 */
@Service
public class AdjudicationWrapper implements IAdjudicationWrapper {
	/**
	 * LIMIT_FEDERAL_DIRECT
	 */
	private static final double LIMIT_FEDERAL_DIRECT  = 179000;
	/**
	 * LIMIT_FEDERAL_GUEST
	 */
    private static final double LIMIT_FEDERAL_GUEST = 616000;
    /**
     * LIMIT_STATE_DIRECT
     */
    private static final double LIMIT_STATE_DIRECT = 29900;
    /**
     * LIMIT_STATE_GUEST
     */
    private static final double LIMIT_STATE_GUEST = 133300;
	/**
	 * @name ValidateAmount
	 * {@abstract Method to save an adjudication }
	 * @param adjudication
	 */
	public String ValidateAmount(Adjudication adjudication) throws Exception {
		if(adjudication.getSourceOrigin().equals(Adjudication.SOURCE_FEDERAL) || adjudication.getSourceOrigin().equals(Adjudication.SOURCE_MIXED)) {
			if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_DIRECT)) {
				if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PERSONS)) {
				if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PUBLIC)) {
				if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
				else if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_GUEST) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
		}
		else if(adjudication.getSourceOrigin().equals(Adjudication.SOURCE_STATE)) {
			if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_DIRECT)) {
				if( adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PERSONS)) {
				if( adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PUBLIC)) {
				if (adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) { 
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
				else if (adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
				else if (adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_GUEST) {
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
			}
		}
		return "";
	}
}
