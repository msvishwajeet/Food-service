package com.foodservice.persistence;
import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.mapper.RestaurenDTO;
import com.foodservice.model.Restaurent;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class JdbiRestaurentPersistence  {
	private final RestaurenDTO restaurenDTO;
	
	@Autowired
	public JdbiRestaurentPersistence(DataSource dataSource) {
		HikariDataSource hikariDataSource = dataSource.getHikariDataSource();
		DBI dbi =  new DBI(hikariDataSource);
		restaurenDTO = dbi.onDemand(RestaurenDTO.class);
	}
	
	public void getAllRestaurent() {
		List<Restaurent> list = restaurenDTO.getall();
		for (Restaurent restaurent : list) {		
			System.out.println(restaurent.getName()+"{["+restaurent.getCityName()+"], ["+restaurent.getStateName()+"]}       Contact Number: "+restaurent.getContactNumber());
		}
	}
	public void insertRestaurent(Restaurent restaurent) {
	restaurenDTO.insertRestaurent(restaurent.getName(),
				restaurent.getContactNumber(), restaurent.getCityName(), restaurent.getStateName(),
				restaurent.getPin(),restaurent.getMenuId());
	}
	
	
	
}
