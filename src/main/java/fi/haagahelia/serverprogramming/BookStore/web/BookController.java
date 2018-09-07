package fi.haagahelia.serverprogramming.BookStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.serverprogramming.BookStore.domain.Book;
import fi.haagahelia.serverprogramming.BookStore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "booklist";
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/save")
	public String save(Book book) {
		bookRepo.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long bookId, Model model) {
		bookRepo.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepo.findById(bookId));
		return "editbook";
	}
}