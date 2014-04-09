package org.wind.k.service.user;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wind.k.entity.User;
import org.wind.k.repository.mybatis.UserMyBatisDao;
import org.wind.k.to.SearchCriteria;
import org.wind.k.to.SearchPageCriteria;

@Service("userService")
public class UserMyBatisService implements IUserService{

	@Autowired
	UserMyBatisDao userDao;
	
	public User findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
	public Page<User> getUserList(SearchCriteria search, Pageable pa) throws Exception {
		
		SearchPageCriteria spc = new SearchPageCriteria();
		BeanUtils.copyProperties(spc, search);
		spc.setPageSize(pa.getPageSize());
		spc.setOffset(pa.getPageNumber() * pa.getPageSize());
		List<User> userList = userDao.getList(spc);
		int totalCount = userDao.getAmount(spc);
		Page<User> userPage = new PageImpl<User>(userList,pa,totalCount);
		
		return userPage;
	}
	public User getUser(Long userId) {
		return userDao.get(userId);
	}

	public void saveUser(User user) {
		if(user.getId() != null){
			userDao.update(user);
		}else{
			userDao.insert(user);
		}
		
	}

}
