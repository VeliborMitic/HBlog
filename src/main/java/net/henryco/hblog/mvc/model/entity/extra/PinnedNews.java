package net.henryco.hblog.mvc.model.entity.extra;

import javax.persistence.*;

/**
 * @author Henry on 17/06/17.
 */
@Entity
public class PinnedNews {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id @Column(name = "id", unique = true)
	private long id;

	@Column(unique = true)
	private long postId;

	@Column
	private boolean actual;


	@Override
	public String toString() {
		return "PinnedNews{" +
				"id=" + id +
				", postId=" + postId +
				", actual=" + actual +
				'}';
	}

	public PinnedNews() {}

	public PinnedNews(long postId) {
		this();
		this.postId = postId;
	}

	public PinnedNews(long postId, boolean actual) {
		this(postId);
		this.actual = actual;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
}
