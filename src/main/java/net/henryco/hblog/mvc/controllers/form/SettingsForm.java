package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 27/06/17.
 */

@Data @NoArgsConstructor
public class SettingsForm {

	@Size(min = 2, max = 20, message = "(first name) field must contains at least 2 characters and max 20")
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

}
