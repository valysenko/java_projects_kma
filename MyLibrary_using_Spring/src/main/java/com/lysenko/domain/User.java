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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="login")
	private String login;

	@Column(name="password")
	private String password;

//	@OneToMany(cascade={CascadeType.ALL})
//	@JoinColumn(name="book_id")
//	private List<Order> orders = new ArrayList<Order>();
	
	@OneToMany(mappedBy="user"/*,cascade={CascadeType.ALL}*/,fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<Order>();
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", orders=" + orders + ", role=" + role + "]";
	}

	@OneToOne(cascade = CascadeType.ALL, targetEntity=Role.class,fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public User(){
		
	}
	
	public User(Integer id, String login, String password, List<Order> orders,
			Role role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.orders = orders;
		this.role = role;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setBooks(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order){
		order.setUser(this);
		orders.add(order);
	}
	
	

}