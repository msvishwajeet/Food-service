package com.foodservice.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Order;

@Service
public class OrderPersistence {
	@Autowired
	private DataSource jdbcLayer;
	
	private String INSERT_QUERY = "insert into ordered (name, orderId, contactNumber,orderItem,bill,dateOfOrder)values(?,?,?,?,?,?)";
	private String SELECT_QUREEY="select * from ordered where id =(select max(id) from ordered where contactNumber=?)";
	public void orderInsert(Order order) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, order.getName());
			pstmt.setString(2, order.getOrderId());
			pstmt.setString(3, order.getContactNumber());
			pstmt.setString(4, order.getOrderedItem());
			pstmt.setInt(5, order.getBill());
			pstmt.setString(6, order.getDate().toString());
			int created = pstmt.executeUpdate();
			if(created != 1) throw new SQLException("Order not inserted.");
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
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Order getOrderDetails(String contactNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		Order order=new Order();
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(SELECT_QUREEY);
			pstmt.setString(1, contactNumber);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order.setName(rs.getString("name"));
				order.setOrderId(rs.getString("dateOfOrder"));
				order.setBill(rs.getInt("bill"));
				order.setOrderedItem(rs.getString("orderItem"));
			}
			return order;
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
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
