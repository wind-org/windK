package org.wind.k.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wind.k.entity.User;
import org.wind.k.to.SearchCriteria;


public interface IUserService {

	public User findByLoginName(String username);
	
	public Page<User> getUserList(SearchCriteria search,Pageable pa) throws Exception;
	
	public User getUser(Long userId);
	
	public void saveUser(User user);
	
}
