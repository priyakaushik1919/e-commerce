package com.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.dto.ProductDto;
import com.e_commerce.entity.Product;
import com.e_commerce.entity.Product2;
import com.e_commerce.entity.Product3;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.ProductRepository2;
import com.e_commerce.repository.ProductRepository3;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    
//    @Autowired
//    private ProductRepository productRepository;
    
  @Autowired
  private ProductRepository2 productRepository2;
    
  @Autowired
  private ProductRepository3 productRepository3;

    public Product addProduct(Product product) {
      
        return productRepository.save(product);
    }

//    public Object addProduct(ProductDto productDto, String productType) {
//        if ("Product".equalsIgnoreCase(productType)) {
//            Product product = new Product();
//            product.setName(productDto.getName());
//            product.setPrice(productDto.getPrice());
//            product.setDescription(productDto.getDescription());
//            return productRepository.save(product);
//        } else if ("Product2".equalsIgnoreCase(productType)) {
//            Product2 product2 = new Product2();
//            product2.setName(productDto.getName());
//            product2.setPrice(productDto.getPrice());
//            product2.setDescription(productDto.getDescription());
//            return productRepository2.save(product2);
//        } else if ("Product3".equalsIgnoreCase(productType)) {
//            Product3 product3 = new Product3();
//            product3.setName(productDto.getName());
//            product3.setPrice(productDto.getPrice());
//            product3.setDescription(productDto.getDescription());
//            return productRepository3.save(product3);
//        } else {
//            throw new IllegalArgumentException("Invalid product type");
//        }
//    }


    public Product updateProduct(Long id, Product product) {
        System.out.println("Updating product with ID: {}: "+ id);
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            productRepository.save(updatedProduct);
            System.out.println("Product with ID {} updated successfully: "+ id);
            return updatedProduct;
        } else {
            System.out.println("Product with ID {} not found!: "+ id);
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        System.out.println("Deleting product with ID: {}: "+ id);
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            System.out.println("Product with ID {} deleted successfully: "+ id);
        } else {
            System.out.println("Product with ID {} not found!: "+ id);
            throw new RuntimeException("Product not found");
        }
    }


	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}


