package logicaDeConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB2Management {

	private ResultSet result;
	public static Connection connection;
	
	/**
	 * Creator Method
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	public DB2Management() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		/*SimpleFactoryCredencial pSimpleFactoryCredential = new SimpleFactoryCredencial();
	
		this.setCredential(pSimpleFactoryCredential.createCredential("credentials/CredencialGoDaddy.json"));
		String host = credential.getHost();	
	    String databaseName = credential.getDatabase();
	    String userName = credential.getUserName();
	    String password = credential.getPassword();
		*/
	    
		this.addConnection();
	
		
	}
	
	/**
	 * 
	 * Constructor method with parameters
	 * 
	 * @param pHost
	 * @param databaseName
	 * @param userName
	 * @param password
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public DB2Management(String urlName, String userName, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
	    connection = this.applyConnection(urlName, userName, password);
	    
	}
	
	/**
	 * 
	 * Method to connect with the database through hot and credentials
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 * 
	 */
	public Connection applyConnection(String pUrl, String userName, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	
	
		Class.forName ("com.ibm.db2.jcc.DB2Driver").newInstance();


	    return ((Connection) DriverManager.getConnection(pUrl,userName,password));
	
		
	}
	
	/**
	 * 
	 * Método para realizar la conexión a la base de datos DB2
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void addConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		if(connection==null) {
			String url = "jdbc:db2://dashdb-txn-sbox-yp-dal09-03.services.dal.bluemix.net:50000/BLUDB";
		    String userName = "mrc32751";
		    String password = "477-g5rxpk80qkq3";
		    
		    connection = this.applyConnection( url, userName, password);
	
		}
		
		
	}
	
	/**
	 * 
	 * Método para obtener los datos directamente de la base
	 * de datos según la tabla del modelo ER requerida y
	 * el atributo que determinará el orden descendente
	 * 
	 * @param pTableName
	 * @return un conjunto con los datos almacenados en la base de datos
	 * @throws SQLException 
	 */
	public ResultSet readAllDataFromDB(String pTableName, String pAtribute) throws SQLException {
		
		return connection.createStatement().executeQuery("select * from " + pTableName + " order by "
				+ pAtribute + " desc");
		
	}

	/**
	 * 
	 * Método para insentar datos en una tabla de la base de datos
	 * 
	 * @param pTableName
	 * @param pParameters
	 * @param pValues
	 * @throws SQLException
	 */
	public void insertNewData(String pTableName, String pParameters, String pValues) throws SQLException {
		
		String statement = "Insert into " + pTableName + "(" + pParameters + ")"
				+ " values (" + pValues + ")";
		
		connection.createStatement().execute(statement);
		
	
	}
	
	/**
	 * 
	 * Método para obtener los datos directamente de la base
	 * de datos según la tabla del modelo ER requerida
	 * 
	 * @param pTableName
	 * @return un conjunto con los datos almacenados en la base de datos
	 * @throws SQLException 
	 */
	public ResultSet readAllDataFromDB(String pTableName) throws SQLException {
		
		return connection.createStatement().executeQuery("select * from " + pTableName);
		
	}

	public void updateSpecificData(String pDataBase, String pTable, String pParameter, String pValue, String pIdParameter, String pId) throws SQLException {
		
		String statement = "UPDATE " + pDataBase + "." + pTable +" SET " + pParameter + " = '"+ pValue + "' WHERE (" + pIdParameter + " = '"+pId+"')";
		
		System.out.println(statement);
		
		connection.createStatement().execute(statement);
		
	}
	
	/**
	 * 
	 * @param pTableName
	 * @param pValue
	 * @param pParameter
	 * @throws SQLException
	 */
	public void deleteData(String pTableName, String pValue, String pParameter) throws SQLException {
		
		String statement = "delete from " + pTableName + " where " + pParameter + " = " + pValue;
		
		connection.createStatement().execute(statement);
		
	}
	

	public static void deleteConnection() throws SQLException {
		
		if(connection!=null) {
			
			connection.close();
			connection=null;
			
		}
		
	}
	
	public ResultSet getNextWeekData(String pTable, String pAtribute) throws SQLException {
		
		String statement = "select * from " + pTable + " where "
				+ pAtribute + " between curdate() and DATE_ADD(curdate(),INTERVAL 7 DAY)" + 
						" order by " + pAtribute;
		
		return connection.createStatement().executeQuery(statement);
		
	}
	
	/**
	 * @return the result
	 */
	public ResultSet getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ResultSet result) {
		this.result = result;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection pConnection) {
		connection = pConnection;
	}

	
	
}
