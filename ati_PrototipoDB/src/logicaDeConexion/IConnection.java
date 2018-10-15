package logicaDeConexion;

import java.sql.Connection;
import java.sql.SQLException;



public interface IConnection {
	
	public abstract Connection applyConnection(String pHost, String databaseName, String userName, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
