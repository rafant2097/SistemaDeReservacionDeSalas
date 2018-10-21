package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeCreacion.SimpleFactoryJavaBeanReservation;
import logicaDeNegocio.DateTimeManager;
import logicaDeNegocio.JavaBeanReservation;
import logicaDeNegocio.ReservationManager;
import logicaDeNegocio.RoomManager;
import logicaDeServiciosWeb.GoogleCalendarManager;

/**
 * Servlet implementation class ReservationComplete
 */
@WebServlet("/ReservationComplete")
public class ReservationComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationComplete() {
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
		
		String dateStart = DateTimeManager.constructDate(request.getParameter("Date"), request.getParameter("StartTime"));
		String dateEnd = DateTimeManager.constructDate(request.getParameter("Date"), request.getParameter("EndTime"));
		
		JavaBeanReservation javaBean = SimpleFactoryJavaBeanReservation.createJavaBean(
				request.getParameter("room"), dateStart, dateEnd, 
				request.getParameter("pEmail"), request.getParameter("Description"));
		
		
		RoomManager roomMg;
		ArrayList<String> pInfoReservationList = this.getReservationStatusList(javaBean);
		
		
		try {
			
			String path = request.getRealPath("service.json");  
			FileInputStream file = new FileInputStream(path);
			
			ReservationManager resManager = new ReservationManager();
			
			roomMg = new RoomManager();
			if(resManager.isReservationAvailable(javaBean)){
				resManager.addNewReservation(javaBean, roomMg);
				}
			HttpSession session = request.getSession();
			
			session.setAttribute("correo",javaBean.getConsumerEmail());
			session.setAttribute("fechaI",javaBean.getDateStart());
			
			this.aplicateCalendar(pInfoReservationList, file);
			
			response.sendRedirect("JSP/ConfirmarReservacion.jsp");
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	private void aplicateCalendar(ArrayList<String> pList, FileInputStream pFile) throws GeneralSecurityException, IOException {
		
		GoogleCalendarManager gcM = new GoogleCalendarManager(pFile);
		
		gcM.applyService(pList);
		
	}
	
	private ArrayList<String> getReservationStatusList(JavaBeanReservation javaBean){
		
		ArrayList<String> pList = new ArrayList<String>();
		String dateStart = javaBean.getDateStart().replaceAll(" ", "T");
		String dateEnd = javaBean.getDateFinish().replaceAll(" ", "T");
		
		
		pList.add(DateTimeManager.fixHour(dateEnd));
		pList.add(DateTimeManager.fixHour(dateStart));
		pList.add(javaBean.getDescription());
		pList.add("TEC Cartago, Cartago, CR");
		pList.add("Reservación de Sala - ati");
		pList.add("rafant2097@gmail.com");
		pList.add(javaBean.getConsumerEmail());
		
		return pList;
		
	}

}
