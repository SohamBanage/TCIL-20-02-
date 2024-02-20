package com.crudUsingSpringBoot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crudUsingSpringBoot.Entity.Products;
import com.crudUsingSpringBoot.Repository.ProductRepo;
import com.crudUsingSpringBoot.Service.ProductService;
import com.crudUsingSpringBoot.controller.ProductController;

@SpringBootTest
class CrudUsingSpringBootApplicationTests {

	@Mock
	private ProductService productService;
	
	@Mock
	private ProductRepo productRepo;
	
	@InjectMocks
	private ProductController productController;
	
	
	@Test
	public void testGetAllProducts() {
		List<Products> productLst = new ArrayList<>();
		productLst.add(new Products(1,"Iphone15",72000));
		productLst.add(new Products(2,"Samsung S24 Ultra",130000));
		
		when(productService.getAllProducts()).thenReturn(productLst);
		
		List<Products> productesponse=productController.getAllProducts();
		
		
		verify(productService,times(1)).getAllProducts();
		
		assertEquals(2,productesponse.size());
		assertEquals("Iphone15", productesponse.get(0).getName());
		assertEquals(72000, productesponse.get(0).getPrice());
		
		assertEquals("Samsung S24 Ultra", productesponse.get(1).getName());
		assertEquals(130000, productesponse.get(1).getPrice());
		
		
		
	}
	
	@Test
	public void testGetProductById() {
		Products products = new Products(1,"Iphone15",72000);
		
		when(productService.getProductById(1L)).thenReturn(products);
		
		Products productsResponse = productController.getProductById(1L);
		
		verify(productService,times(1)).getProductById(1L);
		
		assertEquals("Iphone15", productsResponse.getName());
		assertEquals(72000, productsResponse.getPrice());
		
		
		
	}
	
	@Test
	public void testSaveProduct() {
		Products products = new Products(1,"Iphone15",72000);
		
		when(productService.saveProducts(products)).thenReturn(products);
		
		Products productResponse = productController.saveProducts(products);

		verify(productService, times(1)).saveProducts(productResponse);
		
		assertEquals("Iphone15", productResponse.getName());
		assertEquals(72000, productResponse.getPrice());
		
		
	}
	
	@Test
	public void testDelete() {
		productController.deleteProduct(1L);
		verify(productService).deleteProduct(1L);
		
	}
	
	//need to be correct
	@Test
	public void testUpdateProduct() {
		Long productId = 1L;
        Products existingProduct = new Products(1, "ExistingProduct", 500);
        Products updatedProduct = new Products(2, "ExistingProduct", 500);

        when(productService.updateProducts(eq(updatedProduct), eq(productId))).thenReturn(updatedProduct);

        // When
        Products result = productController.updateProducts(productId, updatedProduct);

        // Then
        assertEquals("ExistingProduct", result.getName());
        assertEquals(500, result.getPrice());
        verify(productService, times(1)).updateProducts(updatedProduct, productId);	
	}
	
	@Test
	public void testGetProductByName() {
		List<Products> productLst= new ArrayList<>();
		productLst.add(new Products(1,"Iphone15",72000));
		productLst.add(new Products(2,"Samsung S24 Ultra",130000));
		
		when(productService.findProductByName("Iphone15")).thenReturn(productLst);
		
		List<Products>productsResponce=productController.getProductByName("Iphone15");
		
		verify(productService,times(1)).findProductByName("Iphone15");
		
		assertEquals(2, productsResponce.size());
		assertEquals("Iphone15", productsResponce.get(0).getName());
		assertEquals(72000, productsResponce.get(0).getPrice());
		assertEquals("Samsung S24 Ultra", productsResponce.get(1).getName());
		assertEquals(130000, productsResponce.get(1).getPrice());
		
	}
	

	
	@Test
	public void testGetProductInPriceRange() {
		List<Products> productLst=new ArrayList<>();
		productLst.add(new Products(1,"Iphone15",72000));
		productLst.add(new Products(2,"Samsung S24 Ultra",130000));
		productLst.add(new Products(3,"Oneplus11R",62000));
		productLst.add(new Products(4, "MI", 9000));
		
		when(productRepo.findProductsInPriceRange(10000,150000)).thenReturn(productLst);
		
		List<Products> productResponce=productController.getProductsInPriceRange(10000, 150000);
		
		verify(productRepo).findProductsInPriceRange(10000,150000);
		
		assertEquals(productLst.size(),productResponce.size());
		assertEquals(productLst.get(0).getName(), productResponce.get(0).getName());
		assertEquals(productLst.get(0).getPrice(), productResponce.get(0).getPrice());
		
		assertEquals(productLst.get(1).getName(), productResponce.get(1).getName());
		assertEquals(productLst.get(1).getPrice(), productResponce.get(1).getPrice());
		
		assertEquals(productLst.get(2).getName(), productResponce.get(2).getName());
		assertEquals(productLst.get(2).getPrice(), productResponce.get(2).getPrice());
		
		assertEquals(productLst.get(3).getName(), productResponce.get(3).getName());
		assertEquals(productLst.get(3).getPrice(), productResponce.get(3).getPrice());
	}
	
	@Test
	public void testGetProductLike() {
		List<Products> productLst=new ArrayList<>();
		productLst.add(new Products(1,"Iphone15",72000));
		productLst.add(new Products(2,"Samsung S24 Ultra",130000));
		productLst.add(new Products(3,"Oneplus11R",62000));
		
		when(productRepo.findProductLike("I")).thenReturn(productLst);
		
		
		List<Products> productsResponse=productController.getProductLike("I");
		
		verify(productRepo).findProductLike("I");
		
		assertEquals("Iphone15", productsResponse.get(0).getName());
		assertEquals(72000, productsResponse.get(0).getPrice());
		
		
	}

}
