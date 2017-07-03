package net.henryco.hblog.mvc.model.dto.account;

import net.henryco.hblog.utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Henry on 18/06/17.
 */
@Entity
public class AuthUserProfile {

	@Column @Id
	private long id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean enabled;

	@Column(nullable = false)
	private boolean locked;

	@Column(nullable = false)
	private boolean expired;

	@Column
	private String authorities;

	public AuthUserProfile() {
	}

	public AuthUserProfile(long id, String password, String ... authorities) {
		this();
		this.id = id;
		this.password = password;
		this.authorities = Utils.arrayToString(authorities);
	}


	@Override
	public String toString() {
		return "AuthUserProfile{" +
				"id=" + id +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", locked=" + locked +
				", expired=" + expired +
				", authorities='" + authorities + '\'' +
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	@Deprecated
	public String getAuthorities() {
		return authorities;
	}

	@Deprecated
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String[] getAuthoritiesArray() {
		return Utils.stringToArray(authorities);
	}

	public void setAuthorityArray(String ... authorities) {
		this.authorities = Utils.arrayToString(authorities);
	}
}
