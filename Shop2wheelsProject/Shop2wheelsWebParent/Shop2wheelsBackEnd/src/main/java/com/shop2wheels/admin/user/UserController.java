package com.shop2wheels.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop2wheels.common.entity.Role;
import com.shop2wheels.common.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> usersList = service.listAll();
		model.addAttribute("userslist", usersList);

		return "users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> rolesList = service.listRoles();

		User user = new User();
		user.setEnabled(true);

		model.addAttribute("user", user);
		model.addAttribute("rolesList", rolesList);

		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		service.save(user);

		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		return "redirect:/users";
	}
}
