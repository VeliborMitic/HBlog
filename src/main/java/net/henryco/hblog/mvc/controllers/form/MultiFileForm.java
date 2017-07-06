package net.henryco.hblog.mvc.controllers.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Henry on 03/07/17.
 */
public class MultiFileForm {

	private List<MultipartFile> files;

	public MultiFileForm() {}

	@Override
	public String toString() {
		return "MultiFileForm{" +
				"files=" + files +
				'}';
	}


	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
