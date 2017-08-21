package net.henryco.hblog.mvc.model.entity.extra;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity @Data @NoArgsConstructor
public class PinnedNews {

	@Id @Column(name = "id", unique = true)
	private long id;

	@Column
	private boolean actual;


	public PinnedNews(long id) {
		this.id = id;
	}

	public PinnedNews(long id, boolean actual) {
		this(id);
		this.actual = actual;
	}

}
