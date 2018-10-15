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
 * Servlet implementation class PasswordManage
 */
@WebServlet("/PasswordManage")
public class PasswordManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordManage() {
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
		
		UserManager resMg;
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("user");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		Boolean state = false;
		HttpSession session = request.getSession();
		
		try {
			
			resMg = new UserManager();
			
			for(JavaBeanUser javaBean : resMg.getUserList()) {
				
				if(javaBean.getCorreo().equals(email) & javaBean.getApellido().equals(lastname)&
						javaBean.getNombre().equals(name)&password.equals(password2)) {
					
					resMg.updateAtributeFromUser(password, javaBean.getId());
					state = true;
					session.setAttribute("cambio","cambio");
					break;
					
					
				}
				
			}
			
			if(!state) {
				response.sendRedirect("JSP/PasswordRestore.jsp");
				return;
			}
			
			response.sendRedirect("index.jsp");
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
