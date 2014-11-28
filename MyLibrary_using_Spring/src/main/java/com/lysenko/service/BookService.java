package com.lysenko.service;

import java.util.List;

import com.lysenko.domain.Book;

public interface BookService {
	 public void addBook(Book book);
	 public List<Book> listBook();
	 public void removeBook(Integer id);
}
