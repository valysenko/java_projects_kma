package com.lysenko.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	@RequestMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {
		System.out.println("HERE");
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/main";
		}
		return "redirect:/user/main";
	}

}
