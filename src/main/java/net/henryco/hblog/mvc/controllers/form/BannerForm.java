package net.henryco.hblog.mvc.controllers.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 05/07/17.
 */
public class BannerForm {

	@Size(min = 2, max = 40, message = "(name) size: <2, 40>")
	private String name;

	@Size(min = 2, max = 400, message = "(link) size: <2, 400>")
	private String link;

	@NotNull(message = "(banner image) cannot be null")
	private MultipartFile image;


	@Override
	public String toString() {
		return "BannerForm{" +
				"name='" + name + '\'' +
				", link='" + link + '\'' +
				", image=" + image +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
