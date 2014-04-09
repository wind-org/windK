package org.wind.k.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wind.k.entity.Role;
import org.wind.k.repository.jpa.RoleJpaDao;

@Service("roleService")
public class RoleJpaService implements IRoleService {

	@Autowired
	RoleJpaDao roleDao;
	
	public List<Role> getAll(){
		
		return (List<Role>) roleDao.findAll();
	}
}
