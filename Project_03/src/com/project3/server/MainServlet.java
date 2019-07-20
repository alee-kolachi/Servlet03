package com.project3.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project3.bean.UserBean;
import com.project3.daoimpl.LoginRegisterDaoImpl;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.xml.internal.serialize.Printer;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
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
		
		
		
		response.setContentType("text/html");
		String xString = "<h3>Welcome " + request.getParameter("username") + "</h3>";

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Main.html");
		requestDispatcher.include(request, response);	
		
		response.getOutputStream().println(xString);			
		
//		response.getOutputStream().println("<div style='height:50%;overflow:auto;'>");
//		response.getOutputStream().println("<table id='table' style='width: 100%;'>");
//
//		response.getOutputStream().println("<tr><th>Username</th><th>Email</th><th>Age</th><th>Edit or Delete</th></tr>");
		
		LoginRegisterDaoImpl loginRegisterDaoImpl = new LoginRegisterDaoImpl();
		ArrayList<UserBean> list = null;
		try {
			list = loginRegisterDaoImpl.getAllRecords();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addRows(response, list);
		
		for(int i=0; i<list.size(); i++) {
			response.getOutputStream().println(request.getParameter("table"));		
				
			response.getOutputStream().println("<script src='Login.js'></script>");		
					
//					"<tr>" + 
//					"    <td>" + list.get(i).getUsername() + "</td>" + 
//					"    <td>" + list.get(i).getEmail() + "</td>" + 
//					"    <td>" + list.get(i).getAge() + "</td>" + 
//					"    <td><button name='edit' id=" + list.get(i).getId() + ">Edit</button><button name='delete' id=" + list.get(i).getId() + ">Delete</button></td>"+
//					"  </tr>"	
//									
//					);
		}
		
		response.getOutputStream().println("</table>");  
		response.getOutputStream().println("</div>"); 
		
		
		if(request.getParameter("edit") != null) {
			System.out.println("Edit clicked");
		}

	}
	public void addRows(HttpServletResponse response, ArrayList list) throws IOException{
		response.getOutputStream().println("var table = document.getElementById('table');" + 
				"	var i;" + 
				"	var row;" + 
				"	var j;" + 
				"	var cell1;" + 
				"	var cell2;" + 
				"	var cell3;" + 
				"	for (i = 0; i < " + list.size() + "; i++) {" + 
				"		row = table.insertRow(i);" + 
				"" + 
				"		cell1 = row.insertCell(0);" + 
				"		cell2 = row.insertCell(1);" + 
				"		cell3 = row.insertCell(2);" + 
				"" + 
				"		cell1.innerHTML = " + list.get("i").getUsername() + 
				"		cell1.innerHTML = " + list.get("i").getEmail() + 
				"		cell2.innerHTML = " + list.get("i").getAge + 
				"	}" + 
				"" + 
				"}" + 
				"");	
	}

}
