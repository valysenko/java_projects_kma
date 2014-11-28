package com.lysenko.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lysenko.domain.Book;
import com.lysenko.service.BookService;

@Controller
public class BookController {
	@Autowired
    private BookService bookService;
	

    @RequestMapping("/admin/management")
    public String listContacts(Map<String, Object> map) {

        map.put("book", new Book());
        map.put("bookList", bookService.listBook());
   //     bookService.addBook(new Book("SF2","Fabien","great"));
        return "book";
    }
    
    @RequestMapping("/")
    public String home() {
        return "redirect:/user-login";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book,
            BindingResult result) {
    	System.out.println("in add");
        bookService.addBook(book);

        return "redirect:/admin/management";
    }

    @RequestMapping("admin/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer contactId) {

        bookService.removeBook(contactId);
        
        return "redirect:/admin/management";
    }
}
