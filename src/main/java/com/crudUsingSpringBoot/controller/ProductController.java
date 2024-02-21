package com.crudUsingSpringBoot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crudUsingSpringBoot.Entity.Products;
import com.crudUsingSpringBoot.Repository.ProductRepo;
import com.crudUsingSpringBoot.Service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller",description = "API's for Authentication")
public class ProductController {

	private final ProductService productService;
	
	private final ProductRepo productRepo;
	
	public ProductController(ProductService productService ,ProductRepo productRepo) {
		this.productService=productService;
		this.productRepo=productRepo;
	}
	
	//get all products
	//GET:http://localhost:8080/products/
	@GetMapping("/getall")
	public List<Products> getAllProducts(){
		return productService.getAllProducts();
	}
	
	//get by id
	//GET:http://localhost:8080/products/1
	@GetMapping("/{id}")
	public Products getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	//post
	//POST:http://localhost:8080/products/saveproduct
	@PostMapping("/saveproduct")
	public Products saveProducts(@RequestBody Products product) {
		return productService.saveProduct(product);
	}
	
	//Delete
	//DELETE:http://localhost:8080/products/delete/2
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

	//updating
	//PUT:http://localhost:8080/products/update/3
	@PutMapping("/update/{id}")
	public Products updateProducts(@PathVariable Long id , @RequestBody Products Products) {
		return productService.updateProducts(Products, id);
	}
	
	//finding the productBy name 
	//GET:http://localhost:8080/products/name/iphone
	@GetMapping("/name/{name}")
	public List<Products> getProductByName(@PathVariable String name){
		return productService.findProductByName(name);
	}
	
	//finding the productBy price
	//GET:http://localhost:8080/products/price/50000.99
	@GetMapping("/price/{price}")
	public List<Products> getProductByPrice(@PathVariable int price){
		return productService.findProductByPrice(price);
	}
	
	//fing price Between Particular range using requestparam
	//GET:http://localhost:8080/products/price?minPrice=10000&maxPrice=20000
	@GetMapping("/price")
	public List<Products> getProductBetweenPrice(@RequestParam ("minPrice") int minPrice,@RequestParam ("maxPrice") int maxPrice){
		return productService.findProductByPriceRange(minPrice, maxPrice);
	}
	
	
	//find price Between particular range using path variable
	//GET:http://localhost:8080/products/{10000}/{20000}
	@GetMapping("/{minPrice}/{maxPrice}")
	public List<Products> getproductbetweenRange(@PathVariable int minPrice ,@PathVariable int maxPrice){
		return productService.findProductByPriceRange(minPrice, maxPrice) ;
	}
	
	
	//using Query Annotation
	//GET:http://localhost:8080/range/products/10000/20000
	@GetMapping("/productrange/{minPrice}/{maxPrice}")
    public List<Products> getProductsInPriceRange(
            @PathVariable("minPrice") int minPrice,
            @PathVariable("maxPrice") int maxPrice) {
        return productRepo.findProductsInPriceRange(minPrice, maxPrice);
    }
	
	//Using Query Annotation find name like 
	//GET:http://localhost:8080/products/productLike/S
	@GetMapping("/productLike/{name}")
	public List<Products> getProductLike(@PathVariable String name){
		return productRepo.findProductLike(name);
	}
	
	
}
