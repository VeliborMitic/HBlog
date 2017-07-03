package net.henryco.hblog.mvc.servives.account;

import net.henryco.hblog.mvc.model.dao.account.base.BaseProfileDao;
import net.henryco.hblog.mvc.model.dao.account.priv.AuthProfileDao;
import net.henryco.hblog.mvc.model.dto.account.details.DetailsUserProfile;
import net.henryco.hblog.mvc.model.dto.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.dto.account.AuthUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Henry on 18/06/17.
 */
@Service
public class AuthUserService implements UserDetailsService {


	private final BaseProfileDao profileDao;
	private final AuthProfileDao passDao;

	@Autowired
	public AuthUserService(BaseProfileDao profileDao,
						   AuthProfileDao passDao) {
		this.profileDao = profileDao;
		this.passDao = passDao;
	}


	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		BaseUserProfile userProfile = profileDao.getProfileByUserNameOrEmail(usernameOrEmail);
		if (userProfile == null) throw new UsernameNotFoundException(usernameOrEmail + " does not exist");
		AuthUserProfile authProfile = passDao.getAuthUserProfile(userProfile.getId());
		if (authProfile == null) throw new UsernameNotFoundException(usernameOrEmail + " exist, but authUserProfile is not accessible");

		return new DetailsUserProfile(authProfile, userProfile.getUserName());
	}

}
