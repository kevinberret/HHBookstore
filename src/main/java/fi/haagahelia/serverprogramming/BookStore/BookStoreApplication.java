package fi.haagahelia.serverprogramming.BookStore;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.serverprogramming.BookStore.domain.Book;
import fi.haagahelia.serverprogramming.BookStore.domain.BookRepository;
import fi.haagahelia.serverprogramming.BookStore.domain.Category;
import fi.haagahelia.serverprogramming.BookStore.domain.CategoryRepository;
import fi.haagahelia.serverprogramming.BookStore.domain.User;
import fi.haagahelia.serverprogramming.BookStore.domain.UserRepository;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookRepo, CategoryRepository catRepo, UserRepository userRepo) {
		return args->{
			Category c1 = new Category("Comédie-ballet");
			Category c2 = new Category("Roman");
			
			catRepo.save(c1);
			catRepo.save(c2);
			
			Book b1 = new Book("Le malade imaginaire", "Molière", "9789756440209", 1673, new BigDecimal(3.5), c1);
			Book b2 = new Book("L'étranger", "Camus", "9780241107782", 1942, new BigDecimal(17.89), c2);
			
			bookRepo.save(b1);
			bookRepo.save(b2);
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);
		};
	}
}