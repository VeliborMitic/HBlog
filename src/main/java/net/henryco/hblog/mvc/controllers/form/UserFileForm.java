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
public class UserFileForm {

	@Size(max = 40, message = "(name) size max is 40")
	private String name;

	@NotNull
	private MultipartFile file;

}
