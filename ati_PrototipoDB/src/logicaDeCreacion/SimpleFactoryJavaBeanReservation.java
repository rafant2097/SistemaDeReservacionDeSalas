package logicaDeCreacion;

import logicaDeNegocio.JavaBeanReservation;

public class SimpleFactoryJavaBeanReservation {
	
	public static JavaBeanReservation createJavaBean(String pRoom, String pDateStart, String pDateFinish,
			String pEmail, String pDescription) {
		
		JavaBeanReservation javaBean = new JavaBeanReservation();
		
		javaBean.setRoomId(pRoom);
		javaBean.setDateFinish(pDateFinish);
		javaBean.setDateStart(pDateStart);
		javaBean.setConsumerEmail(pEmail);
		javaBean.setDescription(pDescription);
		
		return javaBean;
		
		
	}

}
