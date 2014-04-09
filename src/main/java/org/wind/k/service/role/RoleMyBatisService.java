package org.wind.k.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wind.k.entity.Role;
import org.wind.k.repository.mybatis.RoleMyBatisDao;

@Service("roleService")
public class RoleMyBatisService implements IRoleService {

	@Autowired
	RoleMyBatisDao roleDao;
	
	public List<Role> getAll(){
		return roleDao.getAll();
	}
}
