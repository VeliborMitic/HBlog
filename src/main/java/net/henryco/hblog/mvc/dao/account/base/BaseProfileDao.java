package net.henryco.hblog.mvc.dao.account.base;

import net.henryco.hblog.mvc.model.account.BaseUserProfile;

import java.util.List;

/**
 * @author Henry on 18/06/17.
 */
public interface BaseProfileDao {

	BaseUserProfile getProfileById(long id);

	BaseUserProfile getProfileByUserNameOrEmail(String nameOrEmail);

	boolean isProfileExists(long id);

	BaseUserProfile addProfile(BaseUserProfile profile);

	List<BaseUserProfile> getAll();

	List<BaseUserProfile> getMany(int numb);

	void deleteById(long id);
}
