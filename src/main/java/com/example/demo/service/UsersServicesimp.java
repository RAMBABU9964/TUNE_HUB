package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repo.UsersRepo;
@Service
public class UsersServicesimp implements UsersServices {
	@Autowired
	UsersRepo repo;

	public UsersServicesimp(UsersRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public String addUser(Users users) {
		repo.save(users);
		return "User added successfully";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null) {
		return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users users=repo.findByEmail(email);
		String db_pass=users.getPassword();
		if(password.equals(db_pass)) {
			return true;
		}else {
		return false;
	}}

	@Override
	public String getRole(String email) {
		Users users=repo.findByEmail(email);
		return users.getRole();
	}

	@Override
	public Users getUser(String email) {
		
		return repo.findByEmail(email);
	}

	@Override
	public void updateUser(Users users) {
           repo.save(users);		
	}

	

}
