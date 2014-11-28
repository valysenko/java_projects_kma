package com.lysenko.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.service.OrderService;

@Controller
public class AdminController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/admin/main")
	public String adminIndex(Map<String, Object> map) {
		map.put("order", new Order());
		map.put("ordersList", orderService.allOrders());
		
		return "admin_main";

	}
	
	 @RequestMapping("admin/delete/order/{orderId}")
	    public String deleteBook(@PathVariable("orderId") Integer orderId) {
		 
	        orderService.removeOrder(orderId);
	        
	        return "redirect:/admin/main";
	    }
	

}
