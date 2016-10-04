package ControlLayer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBEmployee;
import DBLayer.DBEquipment;
import DBLayer.DBEvent;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBEmployee;
import DBLayer.IFDBEquipment;
import DBLayer.IFDBEvent;
import GUILayer.Customer.menuPanels.TimeClass;
import ModelLayer.Active;
import ModelLayer.Customer;
import ModelLayer.Employee;
import ModelLayer.Equipment;
import ModelLayer.Event;
import ModelLayer.PreRentEquipment;
import ModelLayer.Rent;
import ModelLayer.RentType;
import ModelLayer.Rentable;
import ModelLayer.SportCourt;
import ModelLayer.Status;

public class EventCtr {

	public EventCtr() {
	}

	public ArrayList<Event> findAllEvents() {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents();
		return allEvents;
	}

	public ArrayList<Event> findAllEvents(String wClause, String var) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents(wClause, var);
		return allEvents;
	}

	public Event findEvent(int id) {
		IFDBEvent dbEvent = new DBEvent();
		return dbEvent.findEvent(id);
	}

	public Event findEvent(int id, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		Event event = dbEvent.findEvent(id);
		IFDBCustomer dbCus = new DBCustomer();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;
		double price = event.getPrice();
		price -= price * discount;
		event.setPrice(price);
		return event;
	}

	public int updateEvent(int eventId, String name, String description, int maxParticipants, int minParticipants,
			Timestamp regStartDate, Timestamp regEndDate, Timestamp startDate, Timestamp endDate, String active,
			String status, double price, int employeeId) {
		IFDBEvent dbEvent = new DBEvent();
		Event event = new Event();
		event.setEventId(eventId);
		event.setName(name);
		event.setDescription(description);
		event.setMaxParticipants(maxParticipants);
		event.setMinParticipants(minParticipants);
		event.setRegStartDate(regStartDate);
		event.setRegEndDate(regEndDate);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setActive(Active.valueOf(active));
		event.setStatus(Status.valueOf(status));
		event.setPrice(price);
		IFDBEmployee dbEmp = new DBEmployee();
		event.setEmployee(dbEmp.findEmployee(employeeId));
		return dbEvent.updateEvent(event);
	}

	public int removeEvent(int eventId) {
		IFDBEvent dbEvent = new DBEvent();
		Event event = dbEvent.findEvent(eventId);
		event.setActive(Active.INACTIVE);

		for (Customer cus : event.getParticipants()) {
			deleteParticipant(event.getEventId(), cus.getCustomerId());
		}

		RentCtr rentCtr = new RentCtr();
		ArrayList<Rent> sportCourts = rentCtr.findAllRents("rentType", "EQUIPMENT' and notes like '%|" + eventId + "%");
		ArrayList<Rent> equipments = rentCtr.findAllRents("rentType", "SPORTCOURT' and notes = '" + eventId);

		for (Rent sp : sportCourts) {
			rentCtr.deleteRent(sp.getRentId(), 0);
		}
		for (Rent eq : equipments) {
			rentCtr.deleteRent(eq.getRentId(), 0);
		}

		return dbEvent.updateEvent(event);

	}

	public void insertNew(String name, String description, int maxParticipants, int minParticipants,
			Timestamp regStartDate, Timestamp regEndDate, Timestamp startDate, Timestamp endDate, double price,
			int employeeId) throws Exception {
		IFDBEmployee dbEmp = new DBEmployee();
		Employee employee = dbEmp.findEmployee(employeeId);

		Event event = new Event(name, description, maxParticipants, minParticipants, regStartDate, regEndDate,
				startDate, endDate, price, employee);
		try {
			IFDBEvent dbEvent = new DBEvent();
			DBConnection.startTransaction();
			dbEvent.insertEvent(event);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			;
			throw new Exception("Event not inserted");
		}
	}

	public int addParticipant(int eventId, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		IFDBCustomer dbCus = new DBCustomer();
		Event event = findEvent(eventId, customerId);
		Customer customer = dbCus.findCustomer(customerId);
		int nr = event.getParticipantsNr() + 1;

		if (nr < event.getMaxParticipants()) {
			return dbEvent.addParticipant(event, customer, nr);
		} else if (nr == event.getMaxParticipants()) {
			event.setStatus(Status.UNAVAILABLE);
			dbEvent.updateEvent(event);
			return dbEvent.addParticipant(event, customer, nr);
		}

		return -3;

	}

	public int deleteParticipant(int eventId, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		IFDBCustomer dbCus = new DBCustomer();
		Event event = findEvent(eventId, customerId);
		Customer customer = dbCus.findCustomer(customerId);
		int nr = event.getParticipantsNr() - 1;
		if (event.getStatus().equals(Status.UNAVAILABLE)) {
			event.setStatus(Status.AVAILABLE);
		}
		return dbEvent.deleteParticipant(event, customer, nr);

	}

	public ArrayList<Event> findAllEvents(int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents(customerId);
		return allEvents;
	}

	public ArrayList<Event> findCustomerAvailableEvents(Timestamp now, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		IFDBCustomer dbCus = new DBCustomer();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAvailableEvents(now);

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			double price = event.getPrice();
			price -= price * discount;
			event.setPrice(price);
			checkActive(now, event);
			if (event.getStatus().equals(Status.UNAVAILABLE) || event.getActive().equals(Active.INACTIVE)) {
				allEvents.remove(event);
				i--;
			}
		}
		return allEvents;
	}

	public ArrayList<Event> findCustomerCurrentEvents(Timestamp now, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents(customerId);
		IFDBCustomer dbCus = new DBCustomer();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			double price = event.getPrice();
			price -= price * discount;
			event.setPrice(price);
			checkActive(now, event);
			if (now.after(event.getEndDate()) || event.getActive().equals(Active.INACTIVE)) {
				allEvents.remove(event);
				i--;
			}
		}

		return allEvents;
	}

	public ArrayList<Event> findEmployeeOngoingEvents(Timestamp now, int employeeId) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents("employeeId", String.valueOf(employeeId));

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			checkActive(now, event);
			if (now.after(event.getEndDate()) || event.getActive().equals(Active.INACTIVE)) {
				allEvents.remove(event);
				i--;
			}
		}

		return allEvents;
	}

	public ArrayList<Event> findAllOngoingEvents(Timestamp now) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents();

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			checkActive(now, event);
			if (now.before(event.getStartDate()) || now.after(event.getEndDate())
					|| event.getActive().equals(Active.INACTIVE)) {
				allEvents.remove(event);
				i--;
			}
		}

		return allEvents;
	}

	public ArrayList<Event> findEmployeeAvailableEvents(Timestamp now, int employeeId) {
		IFDBEvent dbEvent = new DBEvent();
		IFDBEmployee dbEmp = new DBEmployee();
		Employee emp = dbEmp.findEmployee(employeeId);
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAvailableEvents(now);

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			checkActive(now, event);
			if (event.getStatus().equals(Status.UNAVAILABLE) || event.getActive().equals(Active.INACTIVE)
					|| event.getEmployee().getEmployeeId() != emp.getEmployeeId()) {
				allEvents.remove(event);
				i--;
			}
		}
		return allEvents;
	}

	public ArrayList<Event> findEmployeeUpcomingEvents(Timestamp now, int employeeId) {
		IFDBEvent dbEvent = new DBEvent();
		IFDBEmployee dbEmp = new DBEmployee();
		Employee emp = dbEmp.findEmployee(employeeId);
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents();

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			checkActive(now, event);
			if (event.getActive().equals(Active.INACTIVE) || event.getEmployee().getEmployeeId() != emp.getEmployeeId()
					|| now.after(event.getRegStartDate())) {
				allEvents.remove(event);
				i--;
			}
		}
		return allEvents;
	}

	public ArrayList<Event> findCustomerUpcomingEvents(Timestamp now, int customerId) {
		IFDBEvent dbEvent = new DBEvent();
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents = dbEvent.getAllEvents();
		IFDBCustomer dbCus = new DBCustomer();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;

		for (int i = 0; i <= allEvents.size() - 1; i++) {
			Event event = allEvents.get(i);
			double price = event.getPrice();
			price -= price * discount;
			event.setPrice(price);
			checkActive(now, event);
			if (now.after(event.getRegStartDate()) || event.getActive().equals(Active.INACTIVE)) {
				allEvents.remove(event);
				i--;
			}
		}

		return allEvents;
	}

	private void checkActive(Timestamp now, Event event) {
		if (event.getRegEndDate().before(now) && event.getParticipantsNr() < event.getMinParticipants()) {
			removeEvent(event.getEventId());
			event.setActive(Active.INACTIVE);
		}
	}

	public void insertEvent(String name, String description, int maxParticipants, int minParticipants,
			Timestamp regStartDate, Timestamp regEndDate, double price, int employeeId, Timestamp startDate,
			Timestamp endDate, ArrayList<PreRentEquipment> eqqs, ArrayList<Rent> sportCourts) throws Exception {
		IFDBCustomer dbCus = new DBCustomer();
		Customer customer = dbCus.findCustomer(0);
		IFDBEmployee dbEmp = new DBEmployee();
		Employee employee = dbEmp.findEmployee(employeeId);
		Timestamp dateRentMade = Timestamp.valueOf(LocalDateTime.now());
		IFDBEquipment dbEq = new DBEquipment();
		Rentable rentable = null;

		for (Rent sp : sportCourts) {
			description += "\n" + ((SportCourt) sp.getRentable()).getSport() + " #"
					+ ((SportCourt) sp.getRentable()).getCourtId() + " | " + TimeClass.comboBoxPeriod(sp.getStartDate())
					+ " - " + TimeClass.comboBoxPeriod(sp.getEndDate()) + " | "
					+ TimeClass.smallDate(sp.getStartDate());
		}

		Event event = new Event(name, description, maxParticipants, minParticipants, regStartDate, regEndDate,
				startDate, endDate, price, employee);

		ArrayList<Rent> eqs = new ArrayList<Rent>();
		for (PreRentEquipment preRentEq : eqqs) {
			for (Equipment eq : preRentEq.getEquipments()) {
				rentable = dbEq.findEquipment(eq.getEqId());
				Rent rentEq = new Rent(dateRentMade, preRentEq.getStartDate(), preRentEq.getEndDate(),
						RentType.valueOf("EQUIPMENT"), eq.getPrice(), String.valueOf(eq.getQuantity()), customer,
						rentable);
				eqs.add(rentEq);
			}
		}
		try {
			IFDBEvent dbEvent = new DBEvent();
			DBConnection.startTransaction();
			dbEvent.insertEvent(event, sportCourts, eqs);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Event not inserted");
		}
	}
}
