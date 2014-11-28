package com.lysenko.dao;

import java.util.List;

import com.lysenko.domain.Book;

public interface BookDAO {

	public void addBook(Book contact);

	public List<Book> listBooks();

	public void removeContact(Integer id);

}
