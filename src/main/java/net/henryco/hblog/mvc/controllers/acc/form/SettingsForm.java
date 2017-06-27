package net.henryco.hblog.mvc.controllers.acc.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 27/06/17.
 */
public class SettingsForm {

	@Size(max = 20, message = "(first name) max size is 20")
	private String firstName;

	@Size(max = 20, message = "(last name) max size is 20")
	private String lastName;

	@Email
	@NotNull
	@Size(min = 1, max = 255, message = "(email) field can not be empty or larger then 255 characters")
	private String email;

	@Size(max = 255, message = "(position) field max size is 255")
	private String position;


	@Size(max = 20, message = "(password) field max size is 20")
	private String password;


	public SettingsForm() {
	}


	@Override
	public String toString() {
		return "SettingsForm{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", position='" + position + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
