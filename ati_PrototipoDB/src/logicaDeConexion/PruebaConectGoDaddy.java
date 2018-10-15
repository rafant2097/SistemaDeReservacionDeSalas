package logicaDeConexion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

import logicaDeNegocio.JavaBeanReservation;
import logicaDeNegocio.JavaBeanUser;
import logicaDeNegocio.ReservationManager;
import logicaDeNegocio.RoomManager;
import logicaDeNegocio.UserManager;


public class PruebaConectGoDaddy {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		
		ReservationManager rM = new ReservationManager();
		
		JavaBeanReservation jB = new JavaBeanReservation();
		
		jB.setConsumerEmail("testing@gmail.com");
		jB.setDateFinish("2018-12-06 12:00:00");
		jB.setDateStart("2018-10-06 11:00:00");
		jB.setRoomId("IdSala001");
		
		rM.addNewReservation(jB, new RoomManager());;
		
		
		
	}

}
