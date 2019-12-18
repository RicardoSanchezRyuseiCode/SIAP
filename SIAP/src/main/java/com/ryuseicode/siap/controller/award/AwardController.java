package com.ryuseicode.siap.controller.award;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @name UserController
 * {@summary Controller class to expose award views}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-23
 */
@Controller
public class AwardController {
	/**
	 * @name creation
	 * @param model
	 * @return
	 */
	@RequestMapping("/award/creation")
	public String creation(@RequestParam(name="adjudicationId", required=false, defaultValue= "0") String adjudicationId, Model model) {
	  model.addAttribute("adjudicationId", adjudicationId);
	  return "award/creation";
	}
	/**
	 * @name pending
	 * @param model
	 * @return
	 */
	@RequestMapping("/award/pending")
	public String pending(Model model) {
	  return "award/pending";
	}
	/**
	 * @name goods
	 * @param model
	 * @return
	 */
	@RequestMapping("/award/asset")
	public String asset(Model model) {
	  return "award/asset";
	}
	/**
	 * @name suppliers
	 * @param model
	 * @return
	 */
	@RequestMapping("/award/suppliers")
	public String suppliers(Model model) {
	  return "award/suppliers";
	}
	/**
	 * @name repring
	 * @param model
	 * @return
	 */
	@RequestMapping("/award/reprint")
	public String reprint(Model model) {
	  return "award/reprint";
	}
}
