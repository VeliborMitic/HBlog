package net.henryco.hblog.mvc.dao.account.base;

import net.henryco.hblog.mvc.model.account.BaseUserProfile;
import net.henryco.hblog.mvc.repository.BaseUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry on 18/06/17.
 */
@Repository
public class BaseProfileDaoImp implements BaseProfileDao {

	private final BaseUserProfileRepository userProfileRepository;

	@Autowired
	public BaseProfileDaoImp(BaseUserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public BaseUserProfile getProfileById(long id) {
		return userProfileRepository.findOne(id);
	}

	@Override
	public boolean isProfileExists(long id) {
		return userProfileRepository.exists(id);
	}

	@Override
	public BaseUserProfile addProfile(BaseUserProfile profile) {
		return userProfileRepository.saveAndFlush(profile);
	}

	@Override
	public List<BaseUserProfile> getAll() {
		return userProfileRepository.findAll();
	}

	@Override
	public List<BaseUserProfile> getMany(int numb) {
		return userProfileRepository.findAllByIdIsNotNullOrderByIdAsc(new PageRequest(0, numb));
	}

	@Override
	public void deleteBaseUserProfile(long id) {
		userProfileRepository.delete(id);
	}

	@Override
	public BaseUserProfile getProfileByUserNameOrEmail(String nameOrEmail) {
		return userProfileRepository.findByUserNameOrEmail(nameOrEmail, nameOrEmail);
	}
}
