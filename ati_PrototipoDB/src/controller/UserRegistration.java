package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicaDeNegocio.JavaBeanUser;
import logicaDeNegocio.UserManager;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
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
		
		request.setCharacterEncoding("UTF-8");
		
		if(!(request.getParameter("lastName").equals("") || request.getParameter("name").equals("")
				|| request.getParameter("password").equals("") || 
				request.getParameter("user").equals("")))
		{
		
			JavaBeanUser javaBean = new JavaBeanUser();
			UserManager userMg;
			javaBean.setApellido(request.getParameter("lastName"));
			javaBean.setContraseña(request.getParameter("password"));
			javaBean.setCorreo(request.getParameter("user"));
			javaBean.setNombre(request.getParameter("name"));
			javaBean.setTipo("user");
			
			
			try {
				userMg = new UserManager();
				userMg.addNewUser(javaBean);
				response.sendRedirect("JSP/FormularioLogIn.jsp");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				response.sendRedirect("JSP/ErrorNotification.jsp");
			}
			
			return;
			
		}
		
		response.sendRedirect("JSP/UserRegistration.jsp");
		
	}

}
