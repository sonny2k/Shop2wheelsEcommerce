package com.shop2wheels.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.shop2wheels.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}