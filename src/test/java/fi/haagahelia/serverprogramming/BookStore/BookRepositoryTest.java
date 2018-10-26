package fi.haagahelia.serverprogramming.BookStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.serverprogramming.BookStore.domain.Book;
import fi.haagahelia.serverprogramming.BookStore.domain.BookRepository;
import fi.haagahelia.serverprogramming.BookStore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepo;
	
	private Book book;
	
	@Test
	public void createNewBook() {
		book = new Book("My book", "Benjamin Décaillet", "23847472716263628", 2018, new BigDecimal(50.25), new Category("Comique"));
		bookRepo.save(book);
		
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = bookRepo.findByTitle("L'étranger");
		Long id = books.get(0).getId();
		
		bookRepo.deleteById(id);
		
		assertThat(bookRepo.findById(id)).isEmpty();
	}
	
	@Test
	public void findBookByTitle() {
		List<Book> books = bookRepo.findByTitle("Le malade imaginaire");
        
		assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Molière");
	}
}
