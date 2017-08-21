package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Henry on 03/07/17.
 */
@Data @NoArgsConstructor
public class MultiFileForm {

	private List<MultipartFile> files;
}
