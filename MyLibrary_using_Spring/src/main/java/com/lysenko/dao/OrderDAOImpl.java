package com.lysenko.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lysenko.domain.Book;
import com.lysenko.domain.Order;
import com.lysenko.domain.User;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDAO userDao;

	public void addOrder(int bookId, String userNAme) {
		
		Book book = (Book) sessionFactory.getCurrentSession().load(Book.class,
				bookId);

		User user = userDao.getUser(userNAme);

		Order o = new Order(user, book);
		book.getOrders().add(o);
		user.addOrder(o);
		sessionFactory.getCurrentSession().save(book);
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public List<Order> allOrders() {
		return sessionFactory.getCurrentSession().createQuery("from Order")
				.list();
	}

	public List<Order> userOrders(String userName) {
		User user = userDao.getUser(userName);
		return user.getOrders();
	}
	
	public void removeOrder(Integer id){
		 Order order = (Order) sessionFactory.getCurrentSession().load(
				 Order.class, id);
	        if (null != order) {
	            sessionFactory.getCurrentSession().delete(order);
	        }		
	}
	
}
