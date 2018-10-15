package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeNegocio.*;

/**
 * Servlet implementation class SessionManagerLogIn
 */
@WebServlet("/SessionManagerLogIn")
public class SessionManagerLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionManagerLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager userMg = null;

		HttpSession session = request.getSession();
		session.setAttribute("usuario", "");

		try {
			
			userMg = new UserManager();
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(userMg==null) 
			return;
		
		else {
			
			String correo = request.getParameter("user");
			String contraseña = request.getParameter("password");
			
			for(JavaBeanUser user: userMg.getUserList()) {
				
				if(user.getCorreo().equals(correo) & 
						user.getContraseña().equals(contraseña)) {
					
					session.setAttribute("usuario", user.getNombre());
					session.setAttribute("correo", correo);
					session.setAttribute("tipo", user.getTipo());
					session.setAttribute("contraseña", user.getContraseña());
					break;
					
				}
				
				
			}
		}
		
		
		if(!session.getAttribute("usuario").equals(""))
			response.sendRedirect("JSP/CorrectLogIn.jsp");
		else
			response.sendRedirect("JSP/FormularioLogIn.jsp");
		
	}

}
