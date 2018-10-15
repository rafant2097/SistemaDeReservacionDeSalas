package logicaDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import logicaDeConexion.DB2Management;

public class ReservationManager{


	private ArrayList<JavaBeanReservation> reservationList;
	DB2Management dbManager;
	
	
	
	/**
	 * @return the dbManager
	 */
	public DB2Management getDbManager() {
		return dbManager;
	}

	/**
	 * @param dbManager the dbManager to set
	 */
	public void setDbManager(DB2Management dbManager) {
		this.dbManager = dbManager;
	}

	public ReservationManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		reservationList = new ArrayList<JavaBeanReservation>();
		dbManager = new DB2Management();
		this.updateList();
		
	}
	
	public ArrayList<JavaBeanReservation> readAllInformation(){

		if(reservationList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		return reservationList;
		
	}
	
	
	
	
	private void obtainRoomsFromDB(){
		

		try {
			
			ResultSet result = dbManager.readAllDataFromDB("Reservaciones","FechaInicio");
			
			while(result.next()) 
				reservationList.add(createModel(result));
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		}
	
		
		public JavaBeanReservation readSpecificInformation(int pNumElement) {
			
			if(reservationList.isEmpty()) 
				this.obtainRoomsFromDB();
			
			return this.reservationList.get(pNumElement);
		}
		
		
		/**
		 * 
		 * Create a model of the data to be insert in the database
		 * 	
		 * @param pResult
		 * @return
		 * @throws SQLException
		 */
		private JavaBeanReservation createModel(ResultSet pResult) throws SQLException {
			
			JavaBeanReservation pReservation = new JavaBeanReservation();
			
			String date = DateTimeManager.getDateOnly(pResult.getString("FechaInicio"));
			String hourEnd = DateTimeManager.getHourOnly(pResult.getString("FechaFinalizacion"));
			String hourStart = DateTimeManager.getHourOnly(pResult.getString("FechaInicio"));
			
			pReservation.setId(pResult.getString("IdReservacion"));
			pReservation.setConsumerEmail(pResult.getString("CorreoSolicitante"));
			pReservation.setDateStart(pResult.getString("FechaInicio"));
			pReservation.setDateFinish(pResult.getString("FechaFinalizacion"));
			pReservation.setEndHour(hourEnd);
			pReservation.setStartHour(hourStart);
			pReservation.setRoomId(pResult.getString("IdSala"));
			pReservation.setDescription(pResult.getString("Descripcion"));
			pReservation.setDate(date);
			
			return pReservation;
			
		}

		/**
		 * @return the reservationList
		 */
		public ArrayList<JavaBeanReservation> getReservationList() {
			return reservationList;
		}

		/**
		 * @param reservationList the reservationList to set
		 */
		public void setReservationList(ArrayList<JavaBeanReservation> reservationList) {
			this.reservationList = reservationList;
		}
		
		
		public void updateList() {
			
			obtainRoomsFromDB();
			
		}
		
		
		public boolean isReservationAvailable(JavaBeanReservation javaBeanRequest) {
			
			for(JavaBeanReservation javaBean: this.getReservationList()) {
				
				if(DateTimeManager.isBetween(javaBeanRequest.getDateStart(), javaBeanRequest.getDateFinish(),
						javaBean.getDateStart(), javaBean.getDateFinish())
						& javaBean.getRoomId().equals(javaBeanRequest.getRoomId()))
						return false;
			}
			
			return true;
			
			
		}
		
		/**
		 * 
		 * Método para obtener datos de una sala determinada
		 * 
		 * @param pId
		 * @param roomManager
		 * @return
		 */
		public JavaBeanRoom getRoomInformation(String pId, RoomManager roomManager) {
			
			return roomManager.getRoomData(pId);
			
			
		}
		
		public void addNewReservation(JavaBeanReservation javaBean, RoomManager roomManager) throws SQLException {
			
			dbManager.insertNewData("Reservaciones", this.getParameters(),
					this.createValues(javaBean, roomManager));
			
			
		}
		
		private String getParameters() {
			
			return "IdReservacion, CorreoSolicitante, FechaInicio, FechaFinalizacion,IdSala,Descripcion";
			
		}
		
		private String createValues(JavaBeanReservation javaBean, RoomManager roomManager) {
			
			
			return "'" + javaBean.getId() + "', '" + javaBean.getConsumerEmail() + "', '" + javaBean.getDateStart() 
			+ "', '" + javaBean.getDateFinish() + "','" + roomManager.getRoomData(javaBean.getRoomId()).getId() + "'"
			+ ", '" + javaBean.getDescription() + "'";
			
			
		}
		
		public String getNextId() {
			
			updateList();
			
			int newIdCounter = this.getReservationList().size()+1;
			
			String zeroToAdd;
			
			if(newIdCounter < 10)
				zeroToAdd = "00";
			else if(newIdCounter < 100)
				zeroToAdd = "0";
			else
				zeroToAdd="";
			
			
			return "ID_RES_" + zeroToAdd + newIdCounter;
			
		}
		
		public String getReservatedRoomData(String pRoomId) {
			
			String result="La sala solicitada está reservada en las siguientes fechas:" + "\n";
			
			for(JavaBeanReservation javaBean : this.reservationList) {
				
				if(javaBean.getRoomId().equals(pRoomId)) {
					
					result += javaBean.getDate() + " desde las " + javaBean.getStartHour()
					+ " hasta las " + javaBean.getEndHour() + "\n";
					
				}
				
			}
			
			return result;
			
		}
		
		public boolean deleteData(String pId) {
			
			try {
				
				this.dbManager.deleteData("Reservaciones", pId, "IdReservacion");
				return true;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			return false;
		}
		
	public ArrayList<JavaBeanReservation> getReservationFromNextWeek() throws SQLException{
		
		ArrayList<JavaBeanReservation> pList = new ArrayList<JavaBeanReservation>();
		
		ResultSet result = dbManager.getNextWeekData("Reservaciones", "FechaInicio");
		
		while(result.next()) 
			pList.add(createModel(result));
		
		return pList;
		
	}	
		
}
