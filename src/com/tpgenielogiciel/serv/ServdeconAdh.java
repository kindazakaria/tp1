package com.tpgenielogiciel.serv;

import java.io.IOException;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServdeconAdh
 */
@WebServlet("/ServdeconAdh")
public class ServdeconAdh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServdeconAdh() {
        super();
        // TODO Auto-generated constructor stub
        
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		  HttpSession session = request.getSession();
		  	session.invalidate();
			String ver = (String) request.getSession().getAttribute("statut");
		  	String vers = (String) request.getSession().getAttribute("idutilisateur");
		  	System.out.println("*************"+ver);
		  	System.out.println("*************"+vers);
		  	
		  	response.sendRedirect("login");
		  	
		  //	this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
