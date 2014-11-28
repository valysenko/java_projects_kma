package com.lysenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lysenko.dao.OrderDAO;
import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.domain.User;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private OrderDAO orderDAO;
	
	@Transactional
	public void addOrder(int book, String user) {
		orderDAO.addOrder(book, user);
	}

	@Transactional
	public List<Order> allOrders() {
		return orderDAO.allOrders();
	}

	@Transactional
	public List<Order> userOrders(String user) {
		return orderDAO.userOrders(user);
	}
	
	@Transactional
	public void removeOrder(Integer id){
		orderDAO.removeOrder(id);
	}

}
