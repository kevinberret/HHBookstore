package fi.haagahelia.serverprogramming.BookStore.domain;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
}