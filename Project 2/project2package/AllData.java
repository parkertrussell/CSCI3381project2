/*-----------------------------------------------------------------------------
 * A collection of Person data using a hashmap that allows for the storing, 
 * adding, removing, and searching of people in Contact Tracing application.
 * @author Ross Adams
 * Course: CSCI 3381-MWF 0900-Fall 2020
 ----------------------------------------------------------------------------*/
package project2package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.Month;

public class AllData {

	private HashMap<String, Person> people;	// Collection of Person objects
	// using id as the key
	private String filename;				// Name of data storage file

	//-----------------------------------------------------------------------------

	/**
	 * Default constructor
	 */
	public AllData() {
		people = new HashMap<String, Person>();
		filename = null;
	} // End default constructor

	//-----------------------------------------------------------------------------

	/**
	 * Overloaded constructor
	 * @param fn filename of data to be imported
	 */
	public AllData(String fn) {
		this();
		filename = fn;
		readFile();
	} // End 1-arg constructor

	//-----------------------------------------------------------------------------

	/**
	 * @param p1 Person to add to collection
	 */
	public void add(Person p1) {
		String tempId = p1.getId();
		people.put(tempId, p1);
	} // End add

	//-----------------------------------------------------------------------------

	/**
	 * @param id of Person to remove from collection
	 * @return Person who was removed
	 */
	public Person remove(String id) {
		return people.remove(id);
	} // End remove

	//-----------------------------------------------------------------------------

	/**
	 * @param p Person to modify
	 * @param c Contact to add
	 */
	public void addContact(Person p, Person c) {
		p.addContact(c.getId());
	} // End addContact

	//-----------------------------------------------------------------------------

	/**
	 * @return a String Iterator of the ids (keys) contained in the hashmap
	 */
	public Iterator<String> getIterator() {
		return people.keySet().iterator();
	}

	//-----------------------------------------------------------------------------

	/**
	 * @param id of the Person you're searching for
	 * @return the Person object with the argument id
	 */
	public Person findPerson(String id) {
		return people.get(id);
	} // End findPerson

	//-----------------------------------------------------------------------------

	/**
	 * @return the number of key-value pairs stored in the hashmap
	 */
	public int size() {
		return people.size();
	} // End size

	//-----------------------------------------------------------------------------

	/**
	 * @return True if the hashmap is empty
	 */
	public boolean isEmpty() {
		return people.isEmpty();
	} // End isEmpty

	//-----------------------------------------------------------------------------

	/**
	 * @param id to search for
	 * @return True if the id key is contained within the hashmap
	 */
	public boolean contains(String id) {
		return people.containsKey(id);
	} // End contains

	//-----------------------------------------------------------------------------

	/*
	 * String representation of the AllData class
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Iterator<String> iter = this.getIterator();
		String toReturn = "";
		while(iter.hasNext()) {
			toReturn += this.findPerson(iter.next()).toString() + "\n";
		}
		return toReturn;
	} // End toString

	//-----------------------------------------------------------------------------
	
	/*
	 * String representation of the AllData class
	 * @see java.lang.Object#toString()
	 */
	public String fnLnToString() {
		Iterator<String> iter = this.getIterator();
		String toReturn = "";
		while(iter.hasNext()) {
			toReturn += this.findPerson(iter.next()).getfNamelName() + "\n";
		}
		return toReturn;
	} // End toString

	//-----------------------------------------------------------------------------

	/**
	 * Reads data from input file
	 */
	private void readFile() {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(filename);
			lineReader = new BufferedReader(fr);
			String line = null;
			while((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				// Pass tokens as arguments to the Person constructor
				// Birthday must be parsed as LocalDate.of(int y,int m,int d)
				Person temp = new Person(tokens[0],tokens[1],tokens[2],
						LocalDate.parse(tokens[3]),
						tokens[4],tokens[5]);
				// Any remaining tokens are the id's of the Person's contacts
				for(int i = 6; i < tokens.length; i++) {
					temp.addContact(tokens[i]);
				}
				// Add Person to collection of people
				people.put(temp.getId(), temp);
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, "
					+ "try different read type.");
			try {
				lineReader = new BufferedReader(
						new InputStreamReader(
								this.getClass().getResourceAsStream(filename)));
				String line = null;
				while((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					// Pass tokens as arguments to the Person constructor
					// Birthday must be parsed as LocalDate.of(int,Month,int)
					Person temp = new Person(tokens[0],tokens[1],tokens[2],
							LocalDate.parse(tokens[3]),
							tokens[4],tokens[5]);
					// Remaining tokens are the id's of the Person's contacts
					for(int i = 6; i < tokens.length; i++) {
						temp.addContact(tokens[i]);
					}
					// Add Person to collection of people
					people.put(temp.getId(), temp);
				}
			} catch (Exception e2) {
				e2.fillInStackTrace();
				System.err.println("there was a problem with the file reader, "
						+ "try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	} // End readFile

	//-----------------------------------------------------------------------------

	/**
	 * Used for saving data between runs
	 */
	public void writeFile() {
		doWrite(filename);
	} // End no-arg writeFile

	//-----------------------------------------------------------------------------

	/**
	 * Used for testing the doWrite function
	 * @param altFileName
	 */
	public void writeFile(String altFileName) {
		doWrite(altFileName);
	} // End writeFile

	//-----------------------------------------------------------------------------

	/**
	 * Writes the data from allData into a file
	 * @param fn is File name of output file
	 */
	private void doWrite(String fn) {
		try {
			FileWriter fw = new FileWriter(fn);
			BufferedWriter outputFile = new BufferedWriter(fw);
			outputFile.write(this.toString());
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Didn't save to " + fn);
		}
	} // End doWrite
} // End class
