package edu.app.persistence;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Confirmation")
public class Confirmation extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Confirmation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userkey = request.getParameter("userkey");

		String cle = "";
		int i = 0;
		while (userkey.charAt(i) != '?') {
			cle += userkey.charAt(i);
			i++;
		}
		request.setAttribute("cle", cle);
		

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/pages/confirmation/confirmation.jsf");
		rd.forward(request, response);

		// request.getRequestDispatcher("/faces/confirmation/confirmationFiche.xhtml").forward(request,
		// response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	
}
