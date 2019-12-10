package com.ryuseicode.siap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @name LoginController
 * {@summary Controller to expose login interface}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-19
 */
@Controller
public class LoginController {
	/**
	 * @name Login 
	 * @param model
	 * @return login view
	 */
	@RequestMapping("/login")
	public String login(Model model) {		
	  return "login";
	}
}
