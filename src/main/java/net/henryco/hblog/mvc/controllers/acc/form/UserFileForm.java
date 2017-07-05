package net.henryco.hblog.mvc.controllers.acc.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 05/07/17.
 */
public class UserFileForm {


	@Size(max = 40, message = "(name) size max is 40")
	private String name;

	@NotNull
	private MultipartFile file;

	@Override
	public String toString() {
		return "UserFileForm{" +
				"name='" + name + '\'' +
				", file=" + file +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
