package com.foodservice.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodservice.model.Admin;

@Service
public class AdminPersistence {
	String INSERT_QUERY = "insert into admins (name,contactNumber,email,password) values (?,?,?,?)";
	String SELECT_QUERY = "select name from admins where email=? and password = ?";
@Autowired
DataSource jdbcLayer;

public void addAdmin(Admin admin){
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
		con = jdbcLayer.getConnection();
		pstmt = con.prepareStatement(INSERT_QUERY);
		pstmt.setString(1, admin.getName());
		pstmt.setString(2, admin.getContactNumber());
		pstmt.setString(3, admin.getEmail());
		pstmt.setString(4, admin.getPassword());
		int check = pstmt.executeUpdate();
		if (check!=1) throw new SQLException("Admin not Inserted");
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
}

public String login(String email, String password) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String result="";
	try {
		con = jdbcLayer.getConnection();
		pstmt = con.prepareStatement(SELECT_QUERY);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			result = rs.getString("name");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		if (rs!= null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	return result;
}
}
