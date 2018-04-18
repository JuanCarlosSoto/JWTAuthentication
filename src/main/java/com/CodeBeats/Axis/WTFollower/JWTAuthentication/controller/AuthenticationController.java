package com.CodeBeats.Axis.WTFollower.JWTAuthentication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CodeBeats.Axis.WTFollower.JWTAuthentication.config.JwtTokenUtil;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.model.AuthToken;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.model.LoginUser;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.model.User;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.repository.UserJpaRepository;

@RestController
@RequestMapping("/token")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserJpaRepository userService;
	
	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException{
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginUser.getUsername(),
						loginUser.getPassword()
						
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final User user = userService.findOne(loginUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new AuthToken(token));
	}

}
