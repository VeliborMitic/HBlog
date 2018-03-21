package net.henryco.hblog.mvc.model.entity.account;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Henry on 18/06/17.
 */
@Entity @Data
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

}
