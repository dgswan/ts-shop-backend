package com.tsystems.tshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tsystems.tshop.domain.Product;

@PropertySource("classpath:queries/product-queries.xml")
@Repository
public class ProductRepository {
	
	private static final String GET_PRODUCT_BY_ID_QUERY = "getProductById";
	private static final String GET_ALL_PRODUCTS_QUERY = "getAllProducts";
	
	@Autowired
	private Environment env;
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public ProductRepository(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public Product getProductById(final Long id) {
		final SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(env.getProperty(GET_PRODUCT_BY_ID_QUERY),
				sqlParameterSource, BeanPropertyRowMapper.newInstance(Product.class));
	}
	
	public List<Product> getProducts() {
		final SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(env.getProperty(GET_ALL_PRODUCTS_QUERY),
				sqlParameterSource, BeanPropertyRowMapper.newInstance(Product.class));
	}

}
