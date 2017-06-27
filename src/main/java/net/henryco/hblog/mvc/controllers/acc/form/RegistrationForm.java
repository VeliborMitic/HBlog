package net.henryco.hblog.mvc.controllers.acc.form;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 18/06/17.
 */
public class RegistrationForm {

	@NotNull
	@Size(min = 5, max = 20, message = "(username) field must contains at least 5 characters and max 20")
	private String userName;

	@Size(min= 2, max = 20, message = "(first name) field must contains at least 2 characters and max 20")
	private String firstName;

	@Size(max = 20, message = "(last name) max size is 20")
	private String lastName;

	@Email @NotNull
	@Size(min = 1, max = 255, message = "(email) field can not be empty or larger then 255 characters")
	private String email;

	@NotNull
	@Size(min = 8, max = 20, message = "(password) field must contains at least 8 characters and max 20")
	private String password;

	@NotNull
	@Size(min = 8, max = 8, message = "Size of (pgp public key) field is 8")
	private String gpgPubKey;


	public RegistrationForm() {
	}

	@Override
	public String toString() {
		return "RegistrationForm{" +
				"userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", gpgPubKey='" + gpgPubKey + '\'' +
				'}';
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGpgPubKey() {
		return gpgPubKey;
	}

	public void setGpgPubKey(String gpgPubKey) {
		this.gpgPubKey = gpgPubKey;
	}
}
