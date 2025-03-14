package com.e_commerce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
	  private String name;
	    private BigDecimal price;
	    private String description;
}
