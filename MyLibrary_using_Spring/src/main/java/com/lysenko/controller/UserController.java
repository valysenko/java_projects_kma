package com.lysenko.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.service.BookService;
import com.lysenko.service.OrderService;

@Controller
public class UserController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private BookService bookService;

	@RequestMapping("/user/main")
	public String userWindow(Map<String, Object> map) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); // get logged in username

		System.out.println(name);
		map.put("book", new Book());
		map.put("bookList", bookService.listBook());
		return "user_main";

	}

	@RequestMapping("/user/orders")
	public String userOrdersWindow(Map<String, Object> map) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); // get logged in username
		
		//creating list of books for showing
		List<Book> lB = new ArrayList<Book>();
		List<Order> temp =new ArrayList<Order>(); 
		temp = orderService.userOrders(name);
		System.out.println("Orders:");
		for(Order o : temp){
			System.out.println(o.getBook());
		} 
		
		for(Order o : temp){
			lB.add(o.getBook());
		} 
		
		map.put("bookList", lB);
		return "user_orders";

	}

	@RequestMapping("/user/create/{bookId}")
	public String makeOrder(@PathVariable("bookId") Integer bookId) {

		Authentication a = SecurityContextHolder.getContext()
				.getAuthentication();
		
		orderService.addOrder(bookId, a.getName());
		return "redirect:/user/main";

	}
}
