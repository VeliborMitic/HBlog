package net.henryco.hblog.mvc.controllers.acc.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 18/06/17.
 */
public class LoginForm {

	@NotNull @Size(min = 5, max = 100)
	private String userName;

	@NotNull @Size(min = 8, max = 20)
	private String password;

	public LoginForm() {
	}


	@Override
	public String toString() {
		return "LoginForm{" +
				"userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
