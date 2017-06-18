package net.henryco.hblog.mvc.dao.account.priv;

import net.henryco.hblog.mvc.model.account.AuthUserProfile;
import net.henryco.hblog.mvc.repository.AuthUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Henry on 18/06/17.
 */
@Repository
public class AuthProfileDaoImp implements AuthProfileDao {

	private final AuthUserProfileRepository authUserProfileRepository;

	@Autowired
	public AuthProfileDaoImp(AuthUserProfileRepository authUserProfileRepository) {
		this.authUserProfileRepository = authUserProfileRepository;
	}

	@Override
	public AuthUserProfile getAuthUserProfile(long id) {
		return authUserProfileRepository.findOne(id);
	}

	@Override
	public AuthUserProfile getAuthUserProfileReference(long id) {
		return authUserProfileRepository.getOne(id);
	}

	@Override
	public void saveAuthUserProfile(AuthUserProfile authUserProfile) {
		authUserProfileRepository.saveAndFlush(authUserProfile);
	}

	@Override
	public void deleteAuthUserProfile(long id) {
		authUserProfileRepository.delete(id);
	}


	@Override
	public void setPassword(long id, String password) {
		AuthUserProfile authUserProfile = authUserProfileRepository.findOne(id);
		authUserProfile.setPassword(password);
	}

	@Override
	public boolean isAuthUserProfileExists(long id) {
		return authUserProfileRepository.exists(id);
	}
}
