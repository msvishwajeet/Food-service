package com.foodservice.persistence;

import java.sql.Connection;
//import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Menu;
@Service
public class MenuPersistence {
	private static final String INSERT_QUERY = "insert into menu (dishName , dishPrice,resturentId) values(?, ?, ?)";
	private static final String INSERT_NON_VEG_QUERY = "insert into menu_non (dishName , dishPrice,restaurentId) values(?, ?, ?)";
	private static final String SELECT_BY_ID_QUERY = "select * from menu where resturentId= ?";
	private static final String SELECT_BY_ID_For_Non_veg = "select * from menu_non where restaurentId= ?";
	@Autowired
	private DataSource jdbcLayer;

	public void createMenu(Menu menu) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, menu.getDishName());
			pstmt.setString(2,menu.getDishPrice());
			pstmt.setString(3,menu.getMenuId());
			int i = pstmt.executeUpdate();
			if (i != 1)throw new SQLException("Menu Not Inserted");
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
	public void createNonVegMenu(Menu menu) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(INSERT_NON_VEG_QUERY);
			pstmt.setString(1, menu.getDishName());
			pstmt.setString(2,menu.getDishPrice());
			pstmt.setString(3,menu.getMenuId());
			int i = pstmt.executeUpdate();
			if (i != 1)throw new SQLException("Menu Not Inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	public Set<Menu> getMenu(String menuId) {
		Set<Menu> set = new HashSet<Menu>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID_QUERY);
			pstmt.setString(1, menuId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setDishName(rs.getString(2));
				menu.setDishPrice(rs.getString(3));
				set.add(menu);
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
	public Set<Menu> getNonVegMenu(String menuId) {
		Set<Menu> set = new HashSet<Menu>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = jdbcLayer.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID_For_Non_veg);
			pstmt.setString(1, menuId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setDishName(rs.getString(2));
				menu.setDishPrice(rs.getString(3));
				set.add(menu);
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
