package logicaDeServiciosWeb;

import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * @author To-in
 *
 */
public abstract class GoogleManager {
	
	protected final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    protected final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    public abstract void applyService(ArrayList<String> pList) throws IOException;
    
	/**
	 * @return the aPPLICATION_NAME
	 */
	public String getAPPLICATION_NAME() {
		return APPLICATION_NAME;
	}
	
	/**
	 * @return the jSON_FACTORY
	 */
	public JsonFactory getJSON_FACTORY() {
		return JSON_FACTORY;
	}
    
    
    
    

}
