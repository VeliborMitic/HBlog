package net.henryco.hblog.mvc.dao.account.priv;

import net.henryco.hblog.mvc.model.account.AuthUserProfile;

/**
 * @author Henry on 18/06/17.
 */
public interface AuthProfileDao {

	AuthUserProfile getAuthUserProfile(long id);

	AuthUserProfile getAuthUserProfileReference(long id);

	void saveAuthUserProfile(AuthUserProfile authUserProfile);

	void deleteAuthUserProfile(long id);

	void setPassword(long id, String password);

	boolean isAuthUserProfileExists(long id);
}
