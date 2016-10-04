package GUILayer.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ControlLayer.CustomerCtr;
import ControlLayer.EmployeeCtr;
import ControlLayer.EquipmentCtr;
import ControlLayer.EquipmentTypeCtr;
import ControlLayer.EventCtr;
import ControlLayer.MembershipCtr;
import ControlLayer.PaymentCtr;
import ControlLayer.RentCtr;
import DBLayer.DBRent;
import DBLayer.IFDBRent;
import DBLayer.testForRent;
import ModelLayer.Customer;
import ModelLayer.Employee;
import ModelLayer.Equipment;
import ModelLayer.EquipmentType;
import ModelLayer.Event;
import ModelLayer.Membership;
import ModelLayer.Payment;
import ModelLayer.Person.AccessLvl;
import ModelLayer.Rent;

public class test {

	public static void main(String[] args) throws Exception {
		// membershipTest();
		// customerTest();
		// paymentTest();
		// employeeTest();
		// eqTypeIdTest();
		// EquipmentTest();
		// eventTest();
		// rentTest();
		// dbRentTest();
		loginTest();
		// ceva();
	}

	private static void EquipmentTest() throws Exception {
		EquipmentCtr eqCtr = new EquipmentCtr();
		Equipment eq = new Equipment();

		// eqCtr.insertNew(1, "tricou", "verde", 10, 15);

		eq = eqCtr.findEquipment(1);
		System.out.println(eq.getName());
	}

	private static void eqTypeIdTest() throws Exception {
		EquipmentTypeCtr ec = new EquipmentTypeCtr();
		EquipmentType eqType = new EquipmentType();

		ec.insertNew("tennis");

		eqType = ec.findEquipmentType(2);
		System.out.println(eqType.getName());
	}

	private static void customerTest() throws Exception {
		CustomerCtr cc = new CustomerCtr();
		Customer cus = new Customer();
		ArrayList<Customer> cust = new ArrayList<Customer>();

		// cc.insertNew("Adi", "Cuco", "address", "city", "zip", "phone",
		// "email", "123456-1111");
		// cc.insertNew("R2D2", "Cuco", "address", "city", "zip", "phone",
		// "email", "123456-0000");
		// cc.insertNew("Nebunu", "Martin", "address", "city", "zip", "phone",
		// "email", "123436-1231");

		cus = cc.findCustomer(3);
		cc.updateCustomer(3, "Nebunu", "Martin", "address", "city", "zip", "phone", "email", "123456-1231", true,
				Timestamp.valueOf(LocalDateTime.now()), "CUSTOMER", "ACTIVE", "Guest", "8246", 100);
		System.out.println(cus.getMembership().getName());

		int a = cc.updateCustomer(2, "Gyany", "Cuco", "address", "city", "zip", "phone", "email", "123456-1212", true,
				Timestamp.valueOf(LocalDateTime.now()), "CUSTOMER", "ACTIVE", "Guest", "5555", 0);
		cus = cc.findCustomer("Gyany Cuco");
		System.out.println(cus.getMembership().getName());
		//
		// cust = cc.findAllCustomers();
		// System.out.println();
		// for (Customer c : cust) {
		// System.out.println(c.getfName() + " " + c.getlName());
		// }
		//
		// cust = cc.findAllCustomers("Cuco");
		// System.out.println();
		// for (Customer c : cust) {
		// System.out.println(c.getfName() + " " + c.getlName());
		// }
	}

	private static void membershipTest() throws Exception {
		MembershipCtr mc = new MembershipCtr();
		Membership ms = new Membership();
		ArrayList<Membership> mss = new ArrayList<Membership>();

		mc.insertNew("Guest", 0, 0);
		mc.insertNew("Silver", 15, 250);

		ms = mc.findMembership("Silver");
		System.out.println(ms.getName() + ": " + ms.getPrice());

		int a = mc.updateMembership("Guest", 1, 1);
		ms = mc.findMembership("Guest");
		System.out.println(ms.getName() + ": " + ms.getPrice());

		mss = mc.findAllMemberships();
		System.out.println();
		for (Membership m : mss) {
			System.out.println(m.getName() + ": " + m.getPrice());
		}
	}

	private static void paymentTest() throws Exception {
		PaymentCtr pc = new PaymentCtr();
		Payment p = new Payment();
		ArrayList<Payment> ps = new ArrayList<Payment>();

		pc.insertNew(1, "Silver");
		pc.insertNew(2, "Guest");

		p = pc.findPayment(2);
		System.out.println(p.getDate());

		ps = pc.findAllPayments();
		for (Payment pp : ps) {
			System.out.println(pp.getPaymentId() + pp.getDate().toString());
		}

		ps = pc.findAllPayments("customerId", "2");
		for (Payment pp : ps) {
			System.out.println(pp.getCustomer().getfName() + " " + pp.getMembership().getName() + " " + pp.getAmount()
					+ " " + pp.getDate());
		}

	}

	private static void employeeTest() throws Exception {
		EmployeeCtr ec = new EmployeeCtr();
		Employee emp = new Employee();
		ArrayList<Employee> empt = new ArrayList<Employee>();

		ec.insertNew("Angajat", "Doru", "address", "city", "zip", "phone", "email", "123456-1231", AccessLvl.EMPLOYEE);
		ec.insertNew("Angajat", "Lica", "address", "city", "zip", "phone", "email", "123456-0140", AccessLvl.EMPLOYEE);

		emp = ec.findEmployee(2);
		System.out.println(emp.getfName());

		empt = ec.findAllEmployees("Angajat");
		System.out.println();
		for (Employee c : empt) {
			System.out.println(c.getfName() + " " + c.getlName());
		}

		int a = ec.updateEmployee(2, "Gyany", "Marius", "address", "city", "zip", "phone", "email", "123456-1212",
				"EMPLOYEE", "ACTIVE", "1235");
		emp = ec.findEmployee("Gyany Marius");
		System.out.println(emp.getPassword());

		empt = ec.findAllEmployees();
		System.out.println();
		for (Employee c : empt) {
			System.out.println(c.getfName() + " " + c.getlName());
		}

		empt = ec.findAllEmployees("zip", "zip");
		System.out.println();
		for (Employee c : empt) {
			System.out.println(c.getZip());
		}

	}

	private static void eventTest() throws Exception {
		EventCtr evc = new EventCtr();

		evc.insertNew("Beer Pong Tournament", "Arunca berea in pahar", 10, 2, Timestamp.valueOf(LocalDateTime.now()),
				Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMonths(1)),
				Timestamp.valueOf(LocalDateTime.now().plusMonths(2)), 100, 5);
				// evc.addParticipant(1, 1);
				// evc.addParticipant(1, 2);

		// evc.deleteParticipant(1, 2);
		Event event = evc.findEvent(1);
		// evc.updateEvent(1, "Razboiul manelelor", "belea de belea", 1, 1,
		// event.getRegStartDate(), event.getRegEndDate(),
		// event.getStartDate(), event.getEndDate(),
		// event.getSportCourt().getCourtId(),
		// event.getEqType().getEqTypeId(), "ACTIVE", "UNAVAILABLE", 999, 1);

		System.out.println(event.getParticipantsNr());
		System.out.println(event.getActive().toString());
		System.out.println(event.getStatus().toString());
	}

	private static void rentTest() throws Exception {
		RentCtr rc = new RentCtr();
		//
		// rc.insertNew(Timestamp.valueOf(LocalDateTime.now()),
		// Timestamp.valueOf(LocalDateTime.now().plusHours(2)),
		// "EQUIPMENT", 610, "5", 1, 2);
		//
		// Rent rent = rc.findRent(37);
		// System.out.println(rent.getRentable().getClass().getName());

		EquipmentTypeCtr eqType = new EquipmentTypeCtr();
		// IFDBRent dbRent = new DBRent();
		// ArrayList<Rent> rents =
		// dbRent.getAvailableEquipment(Timestamp.valueOf(LocalDateTime.now()),
		// Timestamp.valueOf(LocalDateTime.now().plusHours(5)),
		// eqType.findEquipmentType(1));
		// for (Rent r : rents) {
		// System.out.println(r.getRentable().getClass().getName());
		// }
		EquipmentCtr eqCtr = new EquipmentCtr();
		ArrayList<Equipment> eqs = eqCtr.getAvailableEquipment(Timestamp.valueOf(LocalDateTime.now()),
				Timestamp.valueOf(LocalDateTime.now().plusHours(2)), 1, 1);
		for (Equipment eq : eqs) {
			System.out.println(eq.getName());
		}
	}

	private static void loginTest() {
		CustomerCtr cc = new CustomerCtr();
		// System.out.println((cc.login(2, "5555")).toString());
		cc.logout(1);
		cc.logout(2);
		cc.logout(3);

		EmployeeCtr ec = new EmployeeCtr();
		// System.out.println((ec.login(2, "1235")).toString());
		ec.logout(1);

		// System.out.println(Timestamp.valueOf(LocalDateTime.now()).getHours()
		// + ":" + Timestamp.valueOf(LocalDateTime.now()).getMinutes());
	}

	private static void dbRentTest() throws SQLException {
		testForRent test = new testForRent();
		RentCtr rc = new RentCtr();
		Rent rent = rc.findRent(1);
		ArrayList<Rent> rents = test.getRentals(rent.getStartDate(), "Tennis");
		for (Rent r : rents) {
			System.out.println(r.getCustomer().getfName());
		}
	}

	private static void ceva() {
		String test = "42";
		System.out.println(test);
		test += "\ncacat";
		System.out.println(test);
		test+= "\npisat";
		System.out.println(test);

	}
}
