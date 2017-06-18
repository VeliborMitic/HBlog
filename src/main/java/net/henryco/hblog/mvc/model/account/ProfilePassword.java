package net.henryco.hblog.mvc.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Henry on 18/06/17.
 */
@Entity
public class ProfilePassword {

	@Column @Id
	private long id;

	@Column(nullable = false)
	private String password;

	public ProfilePassword() {
	}

	public ProfilePassword(long id) {
		this();
		this.id = id;
	}

	public ProfilePassword(long id, String password) {
		this(id);
		this.password = password;
	}

	@Override
	public String toString() {
		return "ProfilePassword{" +
				"id=" + id +
				", password='" + password + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
