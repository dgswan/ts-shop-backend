package com.tsystems.tshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tsystems.tshop.domain.Client;

@PropertySource("classpath:queries/client-queries.xml")
@Repository
public class ClientRepository {
	
	@Autowired
	private Environment env;
	
	private static final String GET_CLIENT_BY_ID_QUERY = "getClientById";
	private static final String ADD_CLIENT_QUERY = "addClient";
	private static final String GET_ALL_CLIENTS_QUERY = "getAllClients";
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public ClientRepository(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public Client getClient(final Long id) {
		final SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(env.getProperty(GET_CLIENT_BY_ID_QUERY),
				sqlParameterSource, BeanPropertyRowMapper.newInstance(Client.class));
	}
	
	public List<Client> getAllUsers() {
		final SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		return namedParameterJdbcTemplate.query(env.getProperty(GET_ALL_CLIENTS_QUERY),
				sqlParameterSource, BeanPropertyRowMapper.newInstance(Client.class));
	}

	public Number addClient(Client client) {
		final SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", client.getName())
                .addValue("surname", client.getSurname())
                .addValue("email", client.getEmail());
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(env.getProperty(ADD_CLIENT_QUERY),
				sqlParameterSource, keyHolder);
		return keyHolder.getKey();
	}
	
}
