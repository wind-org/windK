package org.wind.k.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.wind.k.entity.Role;

public interface RoleJpaDao extends CrudRepository<Role,Long>{
	
}
