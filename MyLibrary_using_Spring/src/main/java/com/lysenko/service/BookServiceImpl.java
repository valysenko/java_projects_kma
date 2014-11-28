package com.lysenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lysenko.dao.BookDAO;
import com.lysenko.domain.Book;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
    private BookDAO contactDAO;
	
	@Transactional
    public void addBook(Book contact) {
        contactDAO.addBook(contact);
    }
 
    @Transactional
    public List<Book> listBook() {
 
        return contactDAO.listBooks();
    }
 
    @Transactional
    public void removeBook(Integer id) {
        contactDAO.removeContact(id);
    }


}
