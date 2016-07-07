/**
 * 
 */
package com.ravi.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ravi.bank.domain.service.jpa.entities.TransactionType;
import com.ravi.bank.domain.service.jpa.entities.TransactionTypeImpl;

/**
 * Does some basic jdbc operations to check DB is up and well
 * without any ORM in between.
 * 
 * @author Ravi Aghera
 *
 */
@Component(value="jdbcService")
public class JdbcService{

	final private static Logger logger =  LoggerFactory.getLogger(JdbcService.class);
	
	//Injected by Spring
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Simple select * query for transaction_type table
	 * @return
	 */
	public List<TransactionType> getAllTransactionTypes() {
		logger.debug("\n\n\n\n ");
		
		final String sql = "select TRANSACTION_TYPE_ID, TRANSACTION_TYPE_NAME from transaction_type";

		final List<TransactionType> results = jdbcTemplate.query(sql,new ResultSetExtractor<List<TransactionType>>() {

			@Override
			public List<TransactionType> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				
				List<TransactionType> results = new ArrayList<TransactionType>();
				while(rs.next()) {
					TransactionType transType = new TransactionTypeImpl();
					transType.setTransactionTypeId(rs.getLong(1));
					transType.setTransactionTypeName(rs.getString(2));
					results.add(transType);
				}
				
				return results;
			}
		});

		return results;
	}
	
}
