package com.ryuseicode.siap.controller.requisition;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @name RequisitionController
 * {@summary Controller class to expose requisition views}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-23
 */
@Controller
public class RequisitionController {
	/**
	 * @name creation
	 * @param model
	 * @return
	 */
	@RequestMapping("/requisition/creation")
	public String creation(Model model) {
	  return "requisition/creation";
	}
}
