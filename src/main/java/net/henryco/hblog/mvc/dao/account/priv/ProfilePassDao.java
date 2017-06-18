package net.henryco.hblog.mvc.dao.account.priv;

import net.henryco.hblog.mvc.model.account.ProfilePassword;

/**
 * @author Henry on 18/06/17.
 */
public interface ProfilePassDao {

	ProfilePassword getUserProfilePassword(long id);

	void saveUserProfilePassword(ProfilePassword profilePassword);

	void deletePassword(long id);

	void setPassword(ProfilePassword profilePassword);

	boolean isPasswordExists(long id);
}
