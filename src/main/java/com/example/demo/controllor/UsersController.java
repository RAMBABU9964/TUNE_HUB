package com.example.demo.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersServices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {
	UsersServices services;
	public UsersController(UsersServices services) {
		super();
		this.services = services;
	}


	@PostMapping("/registration")
	public String addUsers(@ModelAttribute Users users) {
		boolean userStatus=services.emailExists(users.getEmail());
		if(userStatus==false) {
		services.addUser(users);
		System.out.println("User Added");
		}else {
			System.out.println("User Already Exists");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,
			@RequestParam("password")String password,HttpSession session) {
		if(services.validateUser(email,password)==true) {
			String role=services.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
			return "customerHome";
			}
		}else {
			return "login";
		}
	}
//	@GetMapping("/pay")
//	public String pay(@RequestParam String email) {
//		
//		boolean paymentStatus=false;
//		if(paymentStatus==true) {
//			Users u=services.getUser(email);
//			u.setPremium(true);
//			services.updateUser(u);
//		}
//		return"login";
//	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
     session.invalidate();
		return "login";
	}
	
		
	
	
}
