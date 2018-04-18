package com.CodeBeats.Axis.WTFollower.JWTAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CodeBeats.Axis.WTFollower.JWTAuthentication.model.User;
import com.CodeBeats.Axis.WTFollower.JWTAuthentication.repository.UserJpaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserJpaRepository userService;
    
    @GetMapping(path="/add")		//GetMapping is a shortcut for @RequestMapping(method=GET)
    public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String password, @RequestParam String role, @RequestParam String email) {
    	User n = new User();
    	n.setUsername(username);    	
    	n.setPassword(password);
    	n.setRole(role);
    	n.setEmail(email);
    	userService.save(n);
    	return "Saved";
    }
    // @RequestMapping(value="/users", method = RequestMethod.GET)
    // public List <User> listUser(){
    //    return userService.findAll();
    // }
    
    @GetMapping(path="/list")
    public @ResponseBody Iterable<User> getAllUsers(){
    	return userService.findAll();
    }
}