package fi.haagahelia.serverprogramming.BookStore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.serverprogramming.BookStore.domain.Book;
import fi.haagahelia.serverprogramming.BookStore.domain.BookRepository;
import fi.haagahelia.serverprogramming.BookStore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
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
		model.addAttribute("categories", categoryRepo.findAll());
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
		model.addAttribute("categories", categoryRepo.findAll());
		return "editbook";
	}
	
	// RESTful service to get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) bookRepo.findAll();
	}
	
	// RESTful service to get book by id
	@GetMapping("/book/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepo.findById(bookId);
    } 
}