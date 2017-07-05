package net.henryco.hblog.mvc.model.dao.account.base.files;

import net.henryco.hblog.mvc.model.entity.account.files.BaseUserFile;
import net.henryco.hblog.mvc.model.repository.UserFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry on 05/07/17.
 */
@Repository
public class BaseFilesDaoImp implements BaseFilesDao {

	private final UserFileRepository fileRepository;

	@Autowired
	public BaseFilesDaoImp(UserFileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	@Override
	public BaseUserFile getFile(long id) {
		return fileRepository.getOne(id);
	}

	@Override
	public List<BaseUserFile> getAllFiles(long id) {
		return fileRepository.getAllByUserIDOrderByUpdateTimeDesc(id);
	}

	@Override
	public List<BaseUserFile> getAllFiles(long id, int page, int pageSize) {
		return fileRepository.getAllByUserIDOrderByUpdateTimeDesc(id, new PageRequest(page, pageSize));
	}

	@Override
	public boolean isFileExists(long id) {
		return fileRepository.exists(id);
	}

	@Override
	public BaseUserFile saveFile(BaseUserFile file) {
		return fileRepository.saveAndFlush(file);
	}

	@Override
	public void deleteFile(long id) {
		fileRepository.delete(id);
	}
}
