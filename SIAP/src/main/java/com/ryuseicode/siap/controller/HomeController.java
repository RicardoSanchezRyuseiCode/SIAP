package com.ryuseicode.siap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @name HomeController
 * {@summary Controller class to expose home views}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-18
 */
@Controller
public class HomeController {
	/**
	 * @name homeShortcut
	 * @param model
	 * @return home view
	 */
	@RequestMapping("/")
	public String homeShortcut(Model model) {
	  return "home";
	}
	/**
	 * @name home
	 * @param model
	 * @return home view
	 */
	@RequestMapping("/home")
	public String home(Model model) {
	  return "home";
	}
}
