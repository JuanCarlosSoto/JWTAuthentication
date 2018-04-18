package com.CodeBeats.Axis.WTFollower.JWTAuthentication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.model.User;

@Component
// public interface UserJpaRepository extends JpaRepository<User, Long> {
public interface UserJpaRepository extends CrudRepository<User, Long> {
	User findOne(String username);
	
}
