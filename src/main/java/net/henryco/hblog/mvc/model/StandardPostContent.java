package net.henryco.hblog.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Henry on 16/06/17.
 */
@Entity
@Table(name = "standard_post_content")
public class StandardPostContent {

	@Id @Column(name = "id")
	private long id;

	@Column(length = 102400)
	private String content;


	public StandardPostContent() {
	}

	public StandardPostContent(long id) {
		this.id = id;
	}

	public StandardPostContent(long id, String content) {
		this(id);
		this.content = content;
	}

	@Override
	public String toString() {
		return "StandardPostContent{" +
				"id=" + id +
				", content='" + content + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
