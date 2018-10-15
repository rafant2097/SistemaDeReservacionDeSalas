package logicaDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logicaDeConexion.DB2Management;

public class UserManager{
	
	ArrayList<JavaBeanUser> userList;
	DB2Management dbManager;
	
	public UserManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		dbManager = new DB2Management();
		userList = new ArrayList<JavaBeanUser>();
		this.updateList();
		
	}
	
	public boolean deleteData(String pId) {
		
		try {
			
			this.dbManager.deleteData("Usuarios", pId, "IdUsuario");
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public JavaBeanUser readSpecificInformation(int pNumElement) {
		
		if(userList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		return this.getUserList().get(pNumElement);
	}
	
	
	/**
	 * 
	 * Create a model of the data to be insert in the database
	 * 	
	 * @param pResult
	 * @return
	 * @throws SQLException
	 */
	protected JavaBeanUser createModel(ResultSet pResult) throws SQLException {
		
		JavaBeanUser pUser = new JavaBeanUser();
		
		pUser.setId(pResult.getString("IdUsuario"));
		pUser.setContraseña(pResult.getString("Contraseña"));
		pUser.setCorreo(pResult.getString("Correo"));
		pUser.setNombre(pResult.getString("Nombre"));
		pUser.setTipo(pResult.getString("Tipo"));
		pUser.setApellido(pResult.getString("Apellido"));
		
		
		return pUser;
		
	}
	
	public ArrayList<JavaBeanUser> readAllInformation(){

		if(userList.isEmpty()) 
			this.obtainRoomsFromDB();
		
		return userList;
		
	}
	
	public void updateList() {
		
		this.obtainRoomsFromDB();
		
	}
	
	private void obtainRoomsFromDB(){
		

		try {
			
			ResultSet result = dbManager.readAllDataFromDB("Usuarios");
			
			while(result.next()) 
				userList.add(createModel(result));
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @return the userList
	 */
	public ArrayList<JavaBeanUser> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(ArrayList<JavaBeanUser> userList) {
		this.userList = userList;
	}
	
	public void addNewUser(JavaBeanUser javaBean) throws SQLException {
		
		if(this.isEmailAvailable(javaBean.getCorreo())) {
			dbManager.insertNewData("Usuarios", this.getParameters(),
					this.createValues(javaBean));
		}
		
	}
	
	private String getParameters() {
		
		return "IdUsuario, Tipo, Correo, Nombre, Apellido, Contraseña";
		
	}
	
	private String createValues(JavaBeanUser javaBean) {
		
		
		String values = "'" + this.generateId() + "', '" + javaBean.getTipo() + "', '" + javaBean.getCorreo()
		+ "', '" + javaBean.getNombre() + "','" + javaBean.getApellido() + "'"
		+ ", '" + javaBean.getContraseña() + "'";
		
		return values;
		
		
	}
	
	private String generateId() {
		
		int newIdCounter = this.getUserList().size()+1;
		
		String zeroToAdd;
		
		if(newIdCounter < 10)
			zeroToAdd = "00";
		else if(newIdCounter >99)
			zeroToAdd="";
		else
			zeroToAdd = "0";
		
		return "User" + zeroToAdd.toString() + Integer.toString(newIdCounter);
		
		
	}
	
	
	private boolean isEmailAvailable(String pEmail) {
		
		for(JavaBeanUser javaBean : userList) {
			
			if(pEmail.equals(javaBean.getCorreo()))
				return false;
			
		}
		
		return true;
		
	}
	
	public void updateAtributeFromUser(String pValue, String pId) throws SQLException {
		
		this.dbManager.updateSpecificData("MRC32751", "Usuarios", 
				"Contraseña", pValue, "IdUsuario", pId);
		
		
	}
	
}
