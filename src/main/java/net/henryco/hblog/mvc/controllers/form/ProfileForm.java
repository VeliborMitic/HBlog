package net.henryco.hblog.mvc.controllers.form;

/**
 * @author Henry on 04/07/17.
 */
public class ProfileForm {

	private long id;
	private String username;
	private String name;
	private String position;
	private String email;
	private String roles;
	private boolean enabled;

	@Override
	public String toString() {
		return "ProfileForm{" +
				"id=" + id +
				", username='" + username + '\'' +
				", name='" + name + '\'' +
				", position='" + position + '\'' +
				", email='" + email + '\'' +
				", roles='" + roles + '\'' +
				", enabled=" + enabled +
				'}';
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
