package entities;

import java.io.Serializable;

public class Order implements Serializable{
	private String userName;
	private String bookName;
	private String bookAuthor;
	private int id_order;
	public String getUserName() {
		return userName;
	}
	public String getBookName() {
		return bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public int getOrderId(){
		return id_order;
	}
	public Order(int id,String userName, String bookName, String bookAuthor) {
		this.id_order = id;
		this.userName = userName;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}
	
}
