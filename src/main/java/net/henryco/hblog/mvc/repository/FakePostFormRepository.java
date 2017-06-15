package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.projs.StandardPost;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import java.util.Map;

/**
 * @author Henry on 15/06/17.
 */
@Component
public class FakePostFormRepository  {


	private Map<Long, StandardPost> postMap = new HashMap<>();

	public StandardPost save(StandardPost post) {
		return postMap.put(post.getId(), post);
	}


	public StandardPost getOne(Long id) {
		return postMap.get(id);
	}


	public boolean exists(Long id) {
		return postMap.containsKey(id);
	}

	public void remove(Long id) {
		postMap.remove(id);
	}

	public void deleteAll() {
		postMap.clear();
	}

}
