package net.henryco.hblog.mvc.model.dto.account;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Henry on 18/06/17.
 */
@Entity
public class BaseUserProfile {

	@Column(name = "id") @Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@Column(nullable = false,
			length = 20,
			unique = true)
	private String userName;

	@Column(length = 20)
	private String firstName;

	@Column(length = 20)
	private String lastName;

	@Column(nullable = false,
			unique = true)
	private String email;

	@Column
	private String iconLink;

	@Column
	private String position;

	public BaseUserProfile() {
	}

	public BaseUserProfile(String userName, String firstName, String lastName, String email, String iconLink, String position) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.iconLink = iconLink;
		this.position = position;
	}

	@Override
	public String toString() {
		return "BaseUserProfile{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", iconLink='" + iconLink + '\'' +
				", position='" + position + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
