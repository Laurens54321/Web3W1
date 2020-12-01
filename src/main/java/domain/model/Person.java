package domain.model;

import de.mkammerer.argon2.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static domain.model.Person.Role.*;

public class Person {
	private String userid;
	private String email;
	private String passwordHash;
	private String firstName;
	private String lastName;
	private Role role = guest;

	public enum Role{
		guest,
		administrator
	}

	Argon2 argon2 = Argon2Factory.create();

	public Person() { }

	public Person(String userid, String email, String passwordHash, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPasswordHash(passwordHash);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public Person(String userid, String email, String passwordHash, String firstName, String lastName, String role) {
		setUserid(userid);
		setEmail(email);
		setPasswordHash(passwordHash);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}
	


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid == null || userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email == null || email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}

		// Creating array of string length
		char[] ch = new char[password.length()];

		// Copy character by character into array
		for (int i = 0; i < password.length(); i++) {
			ch[i] = password.charAt(i);
		}

		return argon2.verify(passwordHash, ch);
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setPasswordString(String password) throws IllegalArgumentException {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}

		char[] ch = null;

		try {
			// Creating array of string length
			ch = new char[password.length()];

			// Copy character by character into array
			for (int i = 0; i < password.length(); i++) {
				ch[i] = password.charAt(i);
			}

			// Hash password
			String hash = argon2.hash(10, 65536, 1, ch);

			// Verify password
			if (argon2.verify(hash, ch)) {
				this.passwordHash = hash;
			}
		} finally {
			// Wipe confidential data
			argon2.wipeArray(ch);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException {
		if(firstName == null || firstName.isEmpty()){
			System.out.println(firstName);
			throw new IllegalArgumentException("No firstname given");

		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException {
		if(lastName == null || lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setRole(Role role){
		this.role = role;
	}

	public void setRole(String role){
		if (role == null){
			this.role = guest;
			return;
		}
		switch(role){
			case "guest":
				this.role = guest;
				break;
			case "administrator":
				this.role = administrator;
				break;
		}
	}

	public String getRole(){
		return role.toString();
	}

	public boolean isAdmin(){
		if (role == null) return false;
		if (role == administrator) return true;
		else return false;
	}


	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ", '" + getUserid() + "', " + getEmail();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) return ((Person) obj).getUserid().equals(userid);
		else return false;
	}
}
