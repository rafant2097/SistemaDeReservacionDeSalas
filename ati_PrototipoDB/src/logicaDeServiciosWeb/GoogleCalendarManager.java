package logicaDeServiciosWeb;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;


public class GoogleCalendarManager extends GoogleManager{
	
	private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    public static Calendar service;
    
    
    /**
     * 
     * Constructor method
     * @throws IOException 
     * @throws GeneralSecurityException 
     * 
     */
    public GoogleCalendarManager(FileInputStream pPath) throws GeneralSecurityException, IOException {
    	
    	NetHttpTransport HTTP_TRANSPORT;
    	
    	GoogleCredential credential = GoogleCredential.fromStream(pPath)
        	    .createScoped(SCOPES);
    
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
	                .setApplicationName(APPLICATION_NAME)
	                .build();
		
    }
    
    private EventDateTime createDate(String pDate) {
    	
    	DateTime endDateTime = new DateTime(pDate); // Hay que sumar 6 horas a la deseada
		
    	return new EventDateTime()
		    .setDateTime(endDateTime)
		    .setTimeZone("America/Costa_Rica");
    	
    }
    
    private EventAttendee[] setCurrentlyAttendees(String pEmail, String pResponsable) {
    	
    	return new EventAttendee[] {
			    new EventAttendee().setEmail(pEmail),
			    new EventAttendee().setEmail(pResponsable),
			};
    	
    	
    }
  
    /**
     * 
     * Insert an event in google calendar
     * @throws IOException 
     * 
     */
    public void applyService(ArrayList<String> pList) throws IOException {
    	
    	Event event = new Event()
    		    .setSummary(pList.get(2))
    		    .setLocation(pList.get(3))
    		    .setDescription(pList.get(4));

    	event.setStart(this.createDate(pList.get(1)));

    	event.setEnd(this.createDate(pList.get(0)));

    	event.setAttendees(Arrays.asList(this.setCurrentlyAttendees(pList.get(5), pList.get(6))));

    	event.setReminders(this.setEventRemainder());	
    				
    	service.events().insert("primary", event).setSendNotifications(true).execute();
			
    }
    
    private Event.Reminders setEventRemainder() {
    	
    	return new Event.Reminders()
				.setUseDefault(false)
			    .setOverrides(Arrays.asList(this.setReminder()));
    	
    }
    
    private EventReminder[] setReminder() {
    	
    	return new EventReminder[] {
			    new EventReminder().setMethod("email").setMinutes(24 * 60),
			    new EventReminder().setMethod("popup").setMinutes(10),
			};
    	
    }

}
