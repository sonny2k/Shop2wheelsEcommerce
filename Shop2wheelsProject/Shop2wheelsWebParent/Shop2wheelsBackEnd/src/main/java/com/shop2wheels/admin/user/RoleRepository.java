package com.shop2wheels.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop2wheels.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
