package com.ryuseicode.siap.service.award.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.repository.award.imp.AnnexRepository;
import com.ryuseicode.siap.service.award.intf.IAnnexService;
/**
 * @name AnnexService
 * {@summary Service class to implement the behavior of IAnnexService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Service
public class AnnexService implements IAnnexService {
	/**
	 * AnnexRepository
	 */
	@Autowired
	private AnnexRepository annexRepository;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of annex param output }
	 * @param adjudicationId
	 * @return
	 */
	public List<AnnexParamOutput> GetByAdjudicationId(int adjudicationId) {
		return this.annexRepository.GetByAdjudicationId(adjudicationId);
	}
	/**
	 * @name Save
	 * {@summary Method to save a list of annex }
	 * @param annexs
	 * @return
	 */
	public void Save(int adjudicationId, List<Annex> annexs) {
		// Define new annexs
		ArrayList<Annex> newAnnexs = new ArrayList<Annex>();
		// Get current annex
		List<AnnexParamOutput> currentAnnexs = this.annexRepository.GetByAdjudicationId(adjudicationId);
		// Define dictionary for unique values
		Map<Integer, Annex> distinct = new HashMap<Integer, Annex>();
		// Loop in new annex to remove current annex
		for(Annex newAnnex : annexs) {
			boolean flag = true;
			// Loop in current to check if exist
			for(AnnexParamOutput currentAnnex : currentAnnexs) {
				if(currentAnnex.getAnnex().getAssetId() == newAnnex.getAssetId()) {
					flag = false;
					break;
				}
			}
			if(flag) {
				if(!distinct.containsKey(newAnnex.getAssetId())) {
					newAnnex.setAdjudicationId(adjudicationId);
					newAnnex.setActive(1);
					distinct.put(newAnnex.getAssetId(), newAnnex);
				}
			}
		}
		// Get the new annex
		distinct.forEach((assetId, annex) -> {
			newAnnexs.add(annex);
		});
		// save te new annex
		this.annexRepository.Save(newAnnexs);
	}
}
