package net.henryco.hblog.mvc.servives.account;

import net.henryco.hblog.mvc.model.dao.account.base.files.BaseFilesDao;
import net.henryco.hblog.mvc.model.entity.account.files.BaseUserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 05/07/17.
 */
@Service
public class BaseFileService {

	private final BaseFilesDao filesDao;

	@Autowired
	public BaseFileService(BaseFilesDao filesDao) {
		this.filesDao = filesDao;
	}

	public BaseUserFile getFile(long id) {
		return filesDao.getFile(id);
	}

	public List<BaseUserFile> getAllFiles(long id) {
		return filesDao.getAllFiles(id);
	}

	public List<BaseUserFile> getAllFiles(long id, int page, int pageSize) {
		return filesDao.getAllFiles(id, page, pageSize);
	}

	public boolean isFileExists(long id) {
		return filesDao.isFileExists(id);
	}

	public BaseUserFile saveFile(BaseUserFile file) {
		return filesDao.saveFile(file);
	}

	public void deleteFile(long id) {
		filesDao.deleteFile(id);
	}
}
