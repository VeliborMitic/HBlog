package net.henryco.hblog.mvc.controllers.pub;

import net.henryco.hblog.configurations.WebConfiguration;
import net.henryco.hblog.mvc.controllers.acc.form.MultiFileForm;
import net.henryco.hblog.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 05/07/17.
 */
@Controller
@RequestMapping("/upload")
public class UpLoadController {


	@RequestMapping(method = GET)
	public String home(Model model) {
		model.addAttribute("upload_form", new MultiFileForm());
		return "upload";
	}


	@RequestMapping(value = "/submit", method = POST)
	public String submit(@ModelAttribute MultiFileForm fileForm, Model model) {

		System.out.println("\n___");
		List<String> result = new ArrayList<>();
		for (MultipartFile f: fileForm.getFiles()){
			result.add(Utils.saveMultiPartFileWithNewName(f, "_test_", WebConfiguration.UPLOAD_PATH));
		}
		model.addAttribute("uploaded", result);
		return "upresult";
	}

}
