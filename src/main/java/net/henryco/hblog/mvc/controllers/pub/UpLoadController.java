package net.henryco.hblog.mvc.controllers.pub;

import net.henryco.hblog.mvc.controllers.acc.form.UserFileForm;
import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.entity.account.files.BaseUserFile;
import net.henryco.hblog.mvc.servives.account.BaseFileService;
import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import net.henryco.hblog.utils.Utils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


import java.io.File;

import static java.io.File.separator;
import static net.henryco.hblog.configurations.WebConfiguration.USER_FILE_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 05/07/17.
 */
@Controller
@RequestMapping("/account/profile/files")
public class UpLoadController {

	private final BaseFileService fileService;
	private final BaseProfileService profileService;

	@Autowired
	public UpLoadController(BaseFileService fileService, BaseProfileService profileService) {
		this.fileService = fileService;
		this.profileService = profileService;
	}



	@RequestMapping(method = GET)
	public String home(Model model, Authentication authentication) {

		if (authentication.isAuthenticated()) {

			BaseUserProfile profile = profileService.getUserProfileByUserName(authentication.getName());
			model.addAttribute("files", fileService.getAllFiles(profile.getId()));
			model.addAttribute("username", authentication.getName());
			model.addAttribute("upload_form", new UserFileForm());
			return "files";
		}
		return "redirect:/account/profile";
	}


	@RequestMapping(value = "/upload/{username}", method = POST)
	public String submit(@Valid @ModelAttribute("upload_form") UserFileForm fileForm,
						 @PathVariable("username") String userName,
						 BindingResult bindingResult,
						 Authentication authentication) {

		if (authentication.isAuthenticated() && authentication.getName().equals(userName)) {
			if (bindingResult.hasErrors()) return "files";

			String file = Utils.saveMultiPartFileWithNewName(fileForm.getFile(),
					authentication.getName(), USER_FILE_PATH + userName + separator);

			if (file == null) {
				bindingResult.addError(new ObjectError("upload_form",
						"uploaded file cannot be null")); return "files";
			}

			BaseUserFile baseUserFile = new BaseUserFile();
			baseUserFile.setFile(file);
			baseUserFile.setName(fileForm.getName());
			baseUserFile.setUserID(profileService.getUserProfileByUserName(userName).getId());
			baseUserFile.setUpdateTime(DateTime.now(DateTimeZone.UTC).toDate());
			fileService.saveFile(baseUserFile);
		}
		return "redirect:/account/profile/files";
	}


	@RequestMapping(value = "/delete/{id}", method = POST)
	public String delete(@PathVariable("id") long id, Authentication authentication) {

		if (authentication.isAuthenticated() && fileService.isFileExists(id)) {
			BaseUserProfile profile = profileService.getUserProfileByUserName(authentication.getName());
			BaseUserFile file = fileService.getFile(id);
			if (file.getUserID() == profile.getId()) {
				String fileName = file.getFile();
				fileService.deleteFile(id);
				try {
					new File(fileName).deleteOnExit();
				} catch (Exception ignored) {}
			}
		}
		return "redirect:/account/profile/files";
	}


}
