package net.henryco.hblog.mvc.projs;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Henry on 15/06/17.
 */
public class StandardPost {

	private long id;

	@NotNull
	private String title;

	@NotNull
	private String content;


	public StandardPost() {
	}

	public StandardPost(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public StandardPost(long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
