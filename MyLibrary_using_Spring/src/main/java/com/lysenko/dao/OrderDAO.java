package com.lysenko.dao;

import java.util.List;

import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.domain.User;

public interface OrderDAO {
	public void addOrder(int bookId, String userNAme);

	public List<Order> allOrders();

	public List<Order> userOrders(String userName);
	
	public void removeOrder(Integer id);
	
}
