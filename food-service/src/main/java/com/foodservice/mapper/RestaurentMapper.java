package com.foodservice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.foodservice.model.Restaurent;

public class RestaurentMapper implements ResultSetMapper<Restaurent>{

	public Restaurent map(int ids, ResultSet rs, StatementContext statementContext) throws SQLException {
		// TODO Auto-generated method stub
		Restaurent restaurent = new Restaurent();
		restaurent.setName(rs.getString("restaurentName"));
		restaurent.setContactNumber(rs.getString("contactNumber"));
		return restaurent;
	}

}
