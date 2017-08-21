package net.henryco.hblog.mvc.model.entity.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Henry on 16/06/17.
 */
@Entity @Data @NoArgsConstructor
@Table(name = "standard_post_content")
public class StandardPostContent {

	@Id @Column(name = "id")
	private long id;

	@Column(length = 102400)
	private String content;

	@Column(length = 1024)
	private String attached;

	public StandardPostContent(long id) {
		this.id = id;
	}
	public StandardPostContent(String content) {
		this.content = content;
	}

}
