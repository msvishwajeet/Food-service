package com.foodservice.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Address;
@Service
public class AddressPersistence {
	private static final String INSERT_QUERY = "insert into address (cityName, stateName, pin) values(?, ?, ?)";
	//private static final String SELECT_BY_ID_QUERY = "select * from address where id= ?";
	
	@Autowired
	private DataSource jdbcLayer;
	
	public void createAddress(Address address) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, address.getCityName());
			pstmt.setString(2,address.getStateName());
			pstmt.setInt(3, address.getPinNo());
			int created = pstmt.executeUpdate();
			if(created != 1) throw new SQLException("Address not created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
