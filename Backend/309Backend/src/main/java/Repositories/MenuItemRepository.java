package Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ObjectClasses.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
	
	MenuItem findById(int id);
	List<MenuItem> findByStore(int store);

}