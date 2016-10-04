package DBLayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.Event;
import ModelLayer.Rent;

public interface IFDBEvent {
	// get all events
	public ArrayList<Event> getAllEvents();

	// get all events where wClause = 'var'
	public ArrayList<Event> getAllEvents(String wClause, String var);

	// get all events with this customerId
	public ArrayList<Event> getAllEvents(int customerId);

	// find event by eventId
	public Event findEvent(int eventId);

	// insert new Event
	public int insertEvent(Event event) throws Exception;

	public void insertEvent(Event event, ArrayList<Rent> sportCourts, ArrayList<Rent> eqs) throws SQLException;

	// update event
	public int updateEvent(Event event);

	// add/remove participants and update event
	public int addParticipant(Event event, Customer customer, int participantsNr);

	public int deleteParticipant(Event event, Customer customer, int participantsNr);

	// get available events for selected time
	public ArrayList<Event> getAvailableEvents(Timestamp now);
}
