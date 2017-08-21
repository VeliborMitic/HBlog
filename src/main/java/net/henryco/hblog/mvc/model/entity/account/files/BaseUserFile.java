package net.henryco.hblog.mvc.model.entity.account.files;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Henry on 05/07/17.
 */
@Entity @Data
public class BaseUserFile {

	@Column @Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@Column
	private long userID;

	@Column(nullable = false)
	private String file;

	@Column
	private String name;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

}
