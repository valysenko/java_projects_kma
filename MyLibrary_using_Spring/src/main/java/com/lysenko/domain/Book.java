package com.lysenko.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Proxy(lazy=false)
@Table(name = "book")
public class Book {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "author")
	private String author;
    
    @Column(name = "year")
	private String year;
	
//    @OneToMany(cascade={CascadeType.ALL})
//    @JoinColumn(name="book_id")
//    private List<Order> orders = new ArrayList<Order>();
 
    @OneToMany(mappedBy="book"/*,cascade={CascadeType.ALL}*/,fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<Order>();
    
    
	public Book(){
		
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", year=" + year + "]";
	}

	public Book(String name, String author, String year) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}

