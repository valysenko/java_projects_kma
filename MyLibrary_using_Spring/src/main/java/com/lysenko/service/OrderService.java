package com.lysenko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.domain.User;


public interface OrderService {
	public void addOrder(int book, String user);

	public List<Order> allOrders();

	public List<Order> userOrders(String user);
	
	public void removeOrder(Integer id);
}
