package net.henryco.hblog.mvc.controllers.acc.admin;

import net.henryco.hblog.mvc.controllers.form.BannerForm;
import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import net.henryco.hblog.mvc.servives.extra.SimpExtraMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.File;

import static net.henryco.hblog.configurations.WebConfiguration.UPLOAD_PATH;
import static net.henryco.hblog.utils.Utils.saveMultiPartFileWithNewName;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 06/07/17.
 */
@Controller
@RequestMapping("/account/admin/banners")
public class AdminBannerController {


	private final SimpExtraMediaService mediaService;

	@Autowired
	public AdminBannerController(SimpExtraMediaService mediaService) {
		this.mediaService = mediaService;
	}

	@RequestMapping(method = GET)
	public String banners(Model model) {

		StringBuilder actual = new StringBuilder();
		for (PinnedBanner banner : mediaService.getActualBanners())
			actual.append(banner.getId()).append(", ");
		if (actual.length() >= 2) actual.delete(actual.length() - 2, actual.length());

		model.addAttribute("banners", mediaService.getAllBanners());
		model.addAttribute("actual_banners", actual.toString());
		return "banners";
	}



	@RequestMapping(value = "/actual", method = POST)
	public String setActualBanners(@RequestParam("actual") String actual, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) try {

			for (PinnedBanner banner: mediaService.getActualBanners()) {
				banner.setActual(false);
				mediaService.saveBanner(banner);
			}

			if (actual == null || actual.isEmpty())
				return "redirect:/account/admin/banners";

			actualizeBanners(actual.split(","), mediaService);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/account/admin/banners";
	}


	private static void actualizeBanners(String[] bannerNumbers,
										 SimpExtraMediaService mediaService) throws Exception{
		for (String n: bannerNumbers) {
			long number = Long.valueOf(n);
			if (mediaService.isBannerExists(number)) {
				PinnedBanner banner = mediaService.getBannerById(number);
				banner.setActual(true);
				mediaService.saveBanner(banner);
			}
		}
	}



	@RequestMapping(value = "/stat/delete/{id}", method = POST)
	public String deleteBanner(@PathVariable("id") long id, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) try {
			if (mediaService.isBannerExists(id)) {
				String icon = mediaService.getBannerById(id).getMediaUrl();
				mediaService.deleteBanner(id);
				new File(UPLOAD_PATH + icon).deleteOnExit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/account/admin/banners";
	}



	@RequestMapping(value = "/stat/switch/{id}", method = POST)
	public String switchBannerStat(@PathVariable("id") long id, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			if (mediaService.isBannerExists(id)) {
				PinnedBanner banner = mediaService.getBannerById(id);
				banner.setActual(!banner.isActual());
				mediaService.saveBanner(banner);
			}
		}
		return "redirect:/account/admin/banners";
	}




	@RequestMapping(value = "/add", method = GET)
	public String addNewBanner(Model model) {
		model.addAttribute("banner_form", new BannerForm());
		return "addbanner";
	}





	@RequestMapping("add/submit")
	public String saveBanner(@Valid @ModelAttribute("banner_form") BannerForm bannerForm,
							 BindingResult bindingResult, Authentication authentication) {

		if (bindingResult.hasErrors()) return "addbanner";
		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			String imageLink = saveMultiPartFileWithNewName(bannerForm.getImage(),
					authentication.getName()+"_BANNER_", UPLOAD_PATH);
			if (imageLink == null) {
				bindingResult.addError(new ObjectError("image", "Banner image cannot be null"));
				return "addbanner";
			}

			mediaService.saveBanner(createBanner(imageLink, bannerForm));
		}
		return "redirect:/account/admin/banners";
	}


	private static PinnedBanner createBanner(String imgLink, BannerForm bannerForm) {
		PinnedBanner banner = new PinnedBanner();
		banner.setMediaUrl(imgLink);
		banner.setMediaHref(bannerForm.getLink());
		banner.setName(bannerForm.getName());
		return banner;
	}
}
