package com.lysenko.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lysenko.domain.Book;

@Repository
public class BookDAOImpl implements BookDAO{

	 @Autowired
	 private SessionFactory sessionFactory;
	 
	public void addBook(Book contact) {
		sessionFactory.getCurrentSession().save(contact);
		
	}
	
	 @SuppressWarnings("unchecked")
	public List<Book> listBooks() {
	return sessionFactory.getCurrentSession().createQuery("from Book")
		            .list();
	}
	
	public void removeContact(Integer id) {
		 Book book = (Book) sessionFactory.getCurrentSession().load(
				 Book.class, id);
	        if (null != book) {
	            sessionFactory.getCurrentSession().delete(book);
	        }		
	}

}
