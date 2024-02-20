package com.crudUsingSpringBoot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crudUsingSpringBoot.Entity.Products;

public interface ProductRepo extends JpaRepository<Products, Long>{

	List<Products> findByName(String name);
	
	List<Products> findByPrice(int price);
	
	List<Products> findByPriceBetween(int minPrice, int maxPrice);
	
	//using Query Annotation
	@Query("SELECT P FROM Products P WHERE P.price BETWEEN :minPrice AND :maxPrice")
    List<Products> findProductsInPriceRange( int minPrice, int maxPrice);
	
	@Query("Select P from Products P Where P.name Like :name%")
	List<Products> findProductLike(String name);
	
	
}
