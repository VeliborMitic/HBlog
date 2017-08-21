package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 18/06/17.
 */

@Data @NoArgsConstructor
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

}
