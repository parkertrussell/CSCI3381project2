/*-----------------------------------------------------------------------------
 * Definition of a Person Object which allows for the creation and modification
 * of people's personal data in the Contact Tracing application
 * @author Ross Adams
 * Course: CSCI 3381-MWF 0900-Fall 2020
 ----------------------------------------------------------------------------*/
package project2package;

import java.time.LocalDate;
import java.util.ArrayList;


public class Person {
	private String id;					// 5 digit identification
	private String fName;				// first name
	private String lName;				// last name
	private LocalDate birthday;			// date of birth
	private String phone;				// phone number
	private String status;				// health status (negative, infected, 
										// recovered, deceased, or unknown)
	private ArrayList<String> contacts;	// Array of ids

//-----------------------------------------------------------------------------

	/*
	 * Default constructor
	 */
	public Person() {
		id = "00000";
		fName = "unknown";
		lName = "unknown";
		birthday = LocalDate.now();
		phone = "unknown";
		status = "unknown";
		contacts = new ArrayList<String>();
	} // End no-arg Constructor

//-----------------------------------------------------------------------------

	/**
	 * Overloaded constructor
	 * @param id       5 digit identification
	 * @param fName    first name
	 * @param lName    last name
	 * @param birthday date of birth
	 * @param phone    phone number
	 * @param status   health status (negative, infected, recovered, deceased,
	 *                 or unknown)
	 */
	public Person(String id, String fName, String lName, LocalDate birthday,
			String phone, String status) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.birthday = birthday;
		this.phone = phone;
		this.status = status;
		contacts = new ArrayList<String>();
	} // End Constructor

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's id
	 */
	public String getId() {
		return id;
	} // End getId

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's first name
	 */
	public String getfName() {
		return fName;
	} // End getfName

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's last name
	 */
	public String getlName() {
		return lName;
	} // End getlName

//-----------------------------------------------------------------------------
	
		/**
		 * @return the Person's first name
		 */
		public String getfNamelName() {
			return (fName + " " + lName);
		} // End getfName

	//-----------------------------------------------------------------------------

	/**
	 * @return the Person's birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	} // End getBirthday

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's phone number
	 */
	public String getPhone() {
		return phone;
	} // End getPhone

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's status
	 */
	public String getStatus() {
		return status;
	} // End getStatus

//-----------------------------------------------------------------------------

	/**
	 * @return the Person's contacts
	 */
	public ArrayList<String> getContacts() {
		return contacts;
	} // End getContacts

//-----------------------------------------------------------------------------

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	} // End setId

//-----------------------------------------------------------------------------

	/**
	 * @param fName the first name to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	} // End setfName

//-----------------------------------------------------------------------------

	/**
	 * @param lName the last name to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	} // End setlName

//-----------------------------------------------------------------------------

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	} // End setBirthday

//-----------------------------------------------------------------------------

	/**
	 * @param phone the phone number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	} // End setPhone

//-----------------------------------------------------------------------------

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	} // End setStatus

//-----------------------------------------------------------------------------

	/*
	 * String representation of a Person
	 * @return a single-line comma-separated String
	 */
	@Override
	public String toString() {
		return id + "," + fName + "," + lName + "," + birthday + "," + phone
				+ "," + status + contactToString();
	} // End toString

//-----------------------------------------------------------------------------

	/**
	 * Add an id to the contacts ArrayList if not already present
	 * @param id the id of the Person to add to contacts
	 */
	public void addContact(String id) {
		if (!contacts.contains(id)) {
			contacts.add(id);
		}
	} // End addContact

//-----------------------------------------------------------------------------

	/**
	 * String representation of the contacts ArrayList
	 * @return Comma-separated String
	 */
	public String contactToString() {
		String toReturn = new String();
		if (contacts.size() > 0) {
			for (int i = 0; i < contacts.size(); i++) {
				toReturn += "," + contacts.get(i);
			}
		}
		return toReturn;
	} // End contactToString

//-----------------------------------------------------------------------------

	/*
	 * Test equivalence using id as the unique identifier
	 * @param Person to compare
	 * @return True if the Persons are equal
	 */
	public boolean equals(Person rsp) {
		return id.equals(rsp.getId());
	} // End equals
} // End class
