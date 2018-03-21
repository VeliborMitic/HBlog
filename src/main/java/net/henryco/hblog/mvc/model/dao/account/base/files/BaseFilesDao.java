package net.henryco.hblog.mvc.model.dao.account.base.files;

import net.henryco.hblog.mvc.model.entity.account.files.BaseUserFile;

import java.util.List;

/**
 * @author Henry on 05/07/17.
 */
public interface BaseFilesDao {


	BaseUserFile getFile(long id);

	List<BaseUserFile> getAllFiles(long id);

	List<BaseUserFile> getAllFiles(long id, int page, int pageSize);

	boolean isFileExists(long id);

	BaseUserFile saveFile(BaseUserFile file);

	void deleteFile(long id);
}
