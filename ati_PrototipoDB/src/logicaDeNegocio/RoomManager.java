package logicaDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logicaDeConexion.DB2Management;

public class RoomManager{
	
	private ArrayList<JavaBeanRoom> roomList;
	DB2Management dbManager;
	
	public RoomManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		dbManager = new DB2Management();
		roomList = new ArrayList<JavaBeanRoom>();
		this.updateList();
		
	}
	
	public ArrayList<JavaBeanRoom> readAllInformation(){

		if(roomList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		return roomList;
		
	}
	
	public void updateList() {
		
		this.obtainRoomsFromDB();
		
	}
	
	private void obtainRoomsFromDB(){
		

		try {
			
			ResultSet result = dbManager.readAllDataFromDB("Salas");
			
			while(result.next()) 
				roomList.add(createModel(result));
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		
	}
	
	public JavaBeanRoom readSpecificInformation(int pNumElement) {
		
		if(roomList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		return this.getRoomList().get(pNumElement);
	}
	
	
	/**
	 * 
	 * Create a model of the data to be insert in the database
	 * 	
	 * @param pResult
	 * @return
	 * @throws SQLException
	 */
	protected JavaBeanRoom createModel(ResultSet pResult) throws SQLException {
		
		JavaBeanRoom pSala = new JavaBeanRoom();
		
		pSala.setId(pResult.getString("IdSala"));
		pSala.setNumSala(Integer.parseInt(pResult.getString("Numero_Sala")));
		pSala.setNombre(pResult.getString("Nombre"));
		pSala.setDescripcion(pResult.getString("Descripcion"));
		pSala.setCapacidad(Integer.parseInt(pResult.getString("Capacidad")));
		pSala.setDepartamento(pResult.getString("Departamento"));
		
		
		return pSala;
		
	}

	/**
	 * @return the roomList
	 */
	public ArrayList<JavaBeanRoom> getRoomList() {
		return roomList;
	}

	/**
	 * @param roomList the roomList to set
	 */
	public void setRoomList(ArrayList<JavaBeanRoom> roomList) {
		this.roomList = roomList;
	}

	public JavaBeanRoom getRoomData(String pId) {
		
		JavaBeanRoom room = new JavaBeanRoom();
		
		if(roomList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		for(JavaBeanRoom savedRoom : this.getRoomList()) {
			
			if(savedRoom.getId().equals(pId) | savedRoom.getNombre().equals(pId)) {
				room = savedRoom;
				break;
			}
		}
		
		return room;
		
	}
	
	public boolean deleteData(String pId) {
		
		try {
			
			this.dbManager.deleteData("Salas", pId, "IdSala");
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
}

