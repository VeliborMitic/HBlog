package net.henryco.hblog.mvc.controllers.acc.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 03/07/17.
 */
public class PostForm {

	@NotNull @Size(min = 2, max = 40, message = "(tittle) size: <2, 40>")
	private String title;

	@Size(min = 5, max = 185, message = "(preview short) size <5, 185>")
	private String previewShort;

	@Size(min = 5, max = 700, message = "(preview long) size <5, 700>")
	private String previewLong;

	@Size(min = 1, max = 102400, message = "(content) size <1, 102400>")
	private String content;


	@Override
	public String toString() {
		return "PostForm{" +
				"title='" + title + '\'' +
				", previewShort='" + previewShort + '\'' +
				", previewLong='" + previewLong + '\'' +
				", content='" + content + '\'' +
				'}';
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreviewShort() {
		return previewShort;
	}

	public void setPreviewShort(String previewShort) {
		this.previewShort = previewShort;
	}

	public String getPreviewLong() {
		return previewLong;
	}

	public void setPreviewLong(String previewLong) {
		this.previewLong = previewLong;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
