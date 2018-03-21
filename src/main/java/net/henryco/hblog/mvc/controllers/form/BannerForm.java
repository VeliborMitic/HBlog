package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Henry on 05/07/17.
 */

@Data @NoArgsConstructor
public class BannerForm {

	@Size(min = 2, max = 40, message = "(name) size: <2, 40>")
	private String name;

	@Size(min = 2, max = 400, message = "(link) size: <2, 400>")
	private String link;

	@NotNull(message = "(banner image) cannot be null")
	private MultipartFile image;

}
