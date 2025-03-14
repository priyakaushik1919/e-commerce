package com.e_commerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Product;
import com.e_commerce.entity.Product2;
import com.e_commerce.entity.Product3;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.ProductRepository2;
import com.e_commerce.repository.ProductRepository3;

@Service
public class ProductService {


      @Autowired
      private ProductRepository productRepository;
    
	  @Autowired
	  private ProductRepository2 productRepository2;
	    
	  @Autowired
	  private ProductRepository3 productRepository3;

    public Product addProduct(Product product) {
      
        return productRepository.save(product);
    }
    
    public Product2 addProduct2(Product2 product) {
        
        return productRepository2.save(product);
    }
    
    public Product3 addProduct3(Product3 product) {
        
        return productRepository3.save(product);
    }


    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            productRepository.save(updatedProduct);
            return updatedProduct;
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    
    public Product2 updateProduct2(Long id, Product2 product) {
        Optional<Product2> existingProduct2 = productRepository2.findById(id);
        if (existingProduct2.isPresent()) {
            Product2 updatedProduct = existingProduct2.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            productRepository2.save(updatedProduct);
            return updatedProduct;
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    
    public Product3 updateProduct3(Long id, Product3 product) {
        Optional<Product3> existingProduct3 = productRepository3.findById(id);
        if (existingProduct3.isPresent()) {
            Product3 updatedProduct = existingProduct3.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            productRepository3.save(updatedProduct);
            return updatedProduct;
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public List<?> getProductsByType(String productType) {
        if ("Product".equalsIgnoreCase(productType)) {
            return productRepository.findAll();
        } else if ("Product2".equalsIgnoreCase(productType)) {
            return productRepository2.findAll();
        } else if ("Product3".equalsIgnoreCase(productType)) {
            return productRepository3.findAll();
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
}
    

    public void deleteProduct(Long id, String productType) {
        if ("Product".equalsIgnoreCase(productType)) {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new RuntimeException("Product with ID " + id + " not found in Product repository");
            }
        } else if ("Product2".equalsIgnoreCase(productType)) {
            if (productRepository2.existsById(id)) {
                productRepository2.deleteById(id);
            } else {
                throw new RuntimeException("Product with ID " + id + " not found in Product2 repository");
            }
        } else if ("Product3".equalsIgnoreCase(productType)) {
            if (productRepository3.existsById(id)) {
                productRepository3.deleteById(id);
            } else {
                throw new RuntimeException("Product with ID " + id + " not found in Product3 repository");
            }
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }

}


