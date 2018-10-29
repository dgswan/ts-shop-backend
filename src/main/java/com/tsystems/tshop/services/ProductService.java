package com.tsystems.tshop.services;

import java.util.List;

import com.tsystems.tshop.domain.Product;

public interface ProductService {

	Product getProductById(final Long id);
	
	List<Product> getProducts();
	
}
