package com.lysenko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Proxy(lazy=false)
@Table(name = "orders")
public class Order {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	
	
/*	@Column(name="user_id")
	private int user_id;
	
	@Column(name="book_id")
	private int book_id;*/
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
	private Book book;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Order() {
		
	}
	
//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", user=" + user + ", book=" + book + "]";
//	}

	public Order(User user, Book book) {
		super();
		this.user = user;
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	/*public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
*/


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
