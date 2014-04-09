package org.wind.k.repository.mybatis;

import java.util.List;

import org.wind.k.entity.Role;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-28
 *
 */

@MyBatisRepository
public interface RoleMyBatisDao {
	
	List<Role> getAll();
	
}
