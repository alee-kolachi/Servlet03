package com.project3.server;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project3.bean.UserBean;
import com.project3.daoimpl.LoginRegisterDaoImpl;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginRegister() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
		requestDispatcher.include(request, response);
		
		LoginRegisterDaoImpl loginRegisterDaoImpl = new LoginRegisterDaoImpl();

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBean ubBean = new UserBean();
		ubBean.setUsername(username);
		ubBean.setPassword(password);
		
		
		if(request.getParameter("login") != null) {
			try {
				if(loginRegisterDaoImpl.checkLogin(ubBean)) {

					requestDispatcher = request.getRequestDispatcher("/MainServlet");
					requestDispatcher.forward(request,response);
				}
				else {
					byte x[] = "Login Failed".getBytes();
					response.getWriter().println(x);; 
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("register") != null) {
			try {
				if(!loginRegisterDaoImpl.checkLogin(ubBean)) {
					if(loginRegisterDaoImpl.registerUser(ubBean) != 0) {
						System.out.println("Register Successful");
					}
					else {
						System.out.println("Register Failed");
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
