package entities;

import java.io.Serializable;

public class Book implements Serializable{
	private int id_book;
	private String name;
	private String author;
	private String description;
	public Book(int id,String name, String author,String description) {
		this.id_book = id;
		this.name = name;
		this.author = author;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public String getDescription() {
		return description;
	}
	public int getId(){
		return id_book;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ",description = " + description+"]";
	}
	
}
