package net.henryco.hblog.mvc.model.entity.extra;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity
public class PinnedNews {

	@Id @Column(name = "id", unique = true)
	private long id;

	@Column
	private boolean actual;


	@Override
	public String toString() {
		return "PinnedNews{" +
				"id=" + id +
				", actual=" + actual +
				'}';
	}

	public PinnedNews() {}

	public PinnedNews(long id) {
		this();
		this.id = id;
	}
	public PinnedNews(long id, boolean actual) {
		this(id);
		this.actual = actual;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
}
