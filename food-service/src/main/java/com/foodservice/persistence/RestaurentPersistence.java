package com.foodservice.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Restaurent;

@Service
public class RestaurentPersistence {
	private static final String INSERT_QUERY = "insert into restaurent (restaurentName, contactNumber,cityName,stateName,pin, menuId) values (?, ?, ?,?,?, ?)";
	private static final String SELECT_BY_ID_QUERY = "select * from food_service.restaurent where pin= ?";
	private static final String SELECT_QUERY_FOR_ALL_REST = "select * from food_service.restaurent";
	@Autowired
	private DataSource dataSource;
	
	
	public void createRestaurent(Restaurent restaurent) {
		try {
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(INSERT_QUERY);
			pstmt.setString(1, restaurent.getName());
			pstmt.setString(2, restaurent.getContactNumber());
			pstmt.setString(3, restaurent.getCityName());
			pstmt.setString(4, restaurent.getStateName());
			pstmt.setString(5, restaurent.getPin());
			pstmt.setString(6, restaurent.getMenuId());
			int created = pstmt.executeUpdate();
			if(created != 1) throw new SQLException("Restaurent not created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Set<Restaurent> getAllRestaurentByPin(String pin){
		Set<Restaurent> set = new HashSet<Restaurent>();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID_QUERY);
			pstmt.setString(1, pin);
			ResultSet rs =pstmt.executeQuery();
			while (rs.next()) {
				Restaurent rest = new Restaurent();
				rest.setName(rs.getString(2));
				rest.setContactNumber(rs.getString(3)); 
				rest.setMenuId(rs.getString(7));
				set.add(rest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return set;
	}
	public Set<Restaurent> getTotalRestaurent(){
		Set<Restaurent> set = new HashSet<Restaurent>();
		PreparedStatement pstmt = null;
		Connection con =null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(SELECT_QUERY_FOR_ALL_REST);
			ResultSet rs =pstmt.executeQuery();
			while (rs.next()) {
				Restaurent rest = new Restaurent();
				rest.setName(rs.getString(2));
				rest.setContactNumber(rs.getString(3)); 
				rest.setMenuId(rs.getString("menuId"));
				rest.setStateName(rs.getString("stateName"));
				rest.setCityName(rs.getString("cityName"));
				set.add(rest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
		return set;
	}
}
