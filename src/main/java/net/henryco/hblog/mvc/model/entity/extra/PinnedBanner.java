package net.henryco.hblog.mvc.model.entity.extra;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity @Data
public class PinnedBanner {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id", unique = true)
	private long id;

	@Column
	private String name;

	@Column
	private String mediaUrl;

	@Column(length = 400)
	private String mediaHref;

	@Column
	private boolean actual;

}
