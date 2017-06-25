package net.henryco.hblog.mvc.servives.account;

import net.henryco.hblog.mvc.dao.account.base.BaseProfileDao;
import net.henryco.hblog.mvc.model.account.BaseUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 18/06/17.
 */
@Service
public class BaseProfileService {

	private final BaseProfileDao profileDao;


	@Autowired
	public BaseProfileService(BaseProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	public BaseUserProfile getUserProfileById(long id) {
		return profileDao.getProfileById(id);
	}

	public BaseUserProfile getUserProfileByUserName(String name) {
		return profileDao.getProfileByUserNameOrEmail(name);
	}

	public List<BaseUserProfile> getProfiles(int numb) {
		return profileDao.getMany(numb);
	}

	public List<BaseUserProfile> getAllProfiles() {
		return profileDao.getAll();
	}

	public boolean isProfileExists(long id) {
		return profileDao.isProfileExists(id);
	}
}
