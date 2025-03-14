package com.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.entity.Product;
import com.e_commerce.entity.Product2;
import com.e_commerce.entity.Product3;
import com.e_commerce.security.JWTUtil;
import com.e_commerce.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {


    @Autowired
    private ProductService productService;
    
    @Autowired
    private JWTUtil jwtUtil;

    private boolean isAdminWithId1(String jwtToken) {
    	String token = jwtToken.substring(7);
//        Long userId = jwtUtil.getUserIdFromJWT(token);
        String userRole = jwtUtil.getRoleFromJWT(token);

        return "ADMIN".equals(userRole);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @RequestHeader("Authorization") String token) {

        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can add products.");
        }

        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
    
    @PostMapping("/add2")
    public ResponseEntity<?> addProduct2(@RequestBody Product2 product, @RequestHeader("Authorization") String token) {

        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can add products.");
        }

        Product2 savedProduct = productService.addProduct2(product);
        return ResponseEntity.ok(savedProduct);
    }
    
    @PostMapping("/add3")
    public ResponseEntity<?> addProduct3(@RequestBody Product3 product, @RequestHeader("Authorization") String token) {

        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can add products.");
        }

        Product3 savedProduct = productService.addProduct3(product);
        return ResponseEntity.ok(savedProduct);
    }
    
    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts(@RequestParam("productType") String productType, @RequestHeader("Authorization") String token) {
        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can view products.");
        }
        return ResponseEntity.ok(productService.getProductsByType(productType));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product, @RequestHeader("Authorization") String token) {
        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can update products.");
        }
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
    
    @PutMapping("/update2/{id}")
    public ResponseEntity<?> updateProduct2(@PathVariable("id") long id, @RequestBody Product2 product, @RequestHeader("Authorization") String token) {
        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can update products.");
        }
        Product2 updatedProduct = productService.updateProduct2(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
    
    @PutMapping("/update3/{id}")
    public ResponseEntity<?> updateProduct3(@PathVariable("id") long id, @RequestBody Product3 product, @RequestHeader("Authorization") String token) {
        if (!isAdminWithId1(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can update products.");
        }
        Product3 updatedProduct = productService.updateProduct3(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id, @RequestParam("productType") String productType
    		, @RequestHeader("Authorization") String token) {
    	  if (!isAdminWithId1(token)) {
              return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only Admin with ID=1 can update products.");
          }
            productService.deleteProduct(id, productType);
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully from " + productType);
    }
}


