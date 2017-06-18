package net.henryco.hblog.mvc.servives.account;

import net.henryco.hblog.mvc.dao.account.base.BaseProfileDao;
import net.henryco.hblog.mvc.dao.account.priv.ProfilePassDao;
import net.henryco.hblog.mvc.model.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.account.ProfilePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Henry on 18/06/17.
 */
@Service
public class ExtendedProfileService {

	private final BaseProfileDao profileDao;
	private final ProfilePassDao passDao;

	@Autowired
	public ExtendedProfileService(BaseProfileDao profileDao,
								  ProfilePassDao passDao) {
		this.profileDao = profileDao;
		this.passDao = passDao;
	}

	public void saveUserProfile(BaseUserProfile profile, String password) {

		BaseUserProfile savedProfile = profileDao.addProfile(profile);
		passDao.saveUserProfilePassword(new ProfilePassword(savedProfile.getId(), password));
	}

	public void deleteProfile(long id) {
		passDao.deletePassword(id);
		profileDao.deleteById(id);
	}

	public void setPassword(long id, String password) {
		passDao.setPassword(new ProfilePassword(id, password));
	}

	public ProfilePassword getProfilePassword(long id) {
		return passDao.getUserProfilePassword(id);
	}

	public boolean isProfileExists(long id) {
		return profileDao.isProfileExists(id);
	}

	public boolean isPasswordExists(long id) {
		return passDao.isPasswordExists(id);
	}

	public BaseUserProfile getProfileByNameOrEmail(String nameOrEmail) {
		return profileDao.getProfileByUserNameOrEmail(nameOrEmail);
	}

}
