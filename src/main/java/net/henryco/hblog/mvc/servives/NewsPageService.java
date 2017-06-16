package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.PostFormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Henry on 16/06/17.
 */
@Service
public class NewsPageService {

	private final PostFormDao postFormDao;

	@Autowired
	public NewsPageService(@Qualifier("mockDao") PostFormDao postFormDao) {
		this.postFormDao = postFormDao;
	}




}
