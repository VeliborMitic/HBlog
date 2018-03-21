package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Henry on 04/07/17.
 */

@Data @NoArgsConstructor
public class ProfileForm {

	private long id;
	private String username;
	private String name;
	private String position;
	private String email;
	private String roles;
	private boolean enabled;

}
