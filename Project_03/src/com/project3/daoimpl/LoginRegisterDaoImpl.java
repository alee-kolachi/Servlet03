package com.project3.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;
import com.project3.bean.UserBean;
import com.project3.connection.DBConnection;

public class LoginRegisterDaoImpl {
	PreparedStatement pst;
	Connection con = DBConnection.getConnection();
	
	
	public boolean checkLogin(UserBean ubBean) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Select username, password from logindetail where username = ? AND password = ?";
		pst = con.prepareStatement(sql);
		pst.setString(1, ubBean.getUsername());
		pst.setString(2, ubBean.getPassword());
		ResultSet rst = (ResultSet) pst.executeQuery();
		
		while(rst.next()) {
			return true;
		}
		
		return false;
	}


	public int registerUser(UserBean ubBean) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into logindetail(username, password) values(?,?)";
		pst = con.prepareStatement(sql);
		pst.setString(1, ubBean.getUsername());
		pst.setString(2, ubBean.getPassword());
		return pst.executeUpdate();
	}
	public ArrayList getAllRecords() throws SQLException {
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		
		String sql = "Select id, username, email, age from logindetail";
		pst = con.prepareStatement(sql);
		ResultSet rst = (ResultSet) pst.executeQuery();
		
		while(rst.next()) {
			UserBean userBean = new UserBean();
			userBean.setUsername(rst.getString("username"));
			userBean.setEmail(rst.getString("email"));
			userBean.setAge(rst.getInt("age"));
			userBean.setId(rst.getInt("id"));
			
		    list.add(userBean);
		}
		return list;
		
	}

}
