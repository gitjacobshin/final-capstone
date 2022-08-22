package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.techelevator.services.uploads.UploadProvider;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.dto.User;
import com.techelevator.model.dao.UserDAO;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;
	private UploadProvider uploadProvider;

	@Autowired
	public UserController(UserDAO userDAO, UploadProvider uploadProvider) {
		this.userDAO = userDAO;
		this.uploadProvider = uploadProvider;
	}

	//-------------------------------------------------------------------REGISTERING
	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}

	@RequestMapping(path="/users/new", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {

		boolean isUserNameAvailable = userDAO.isUserNameAvailable(user.getUserName());
		if(!isUserNameAvailable) {
			FieldError error = new FieldError("user", "userName","The UserName is not available.");
			result.addError(error);
		}

		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}

		userDAO.saveUser(user.getUserName(), user.getPassword());

		return "redirect:/login";
	}

	@RequestMapping(path="/users/create", method=RequestMethod.POST)
	public String createProfile(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {

		if (result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/create";
		}

		return "redirect:/login";
	}


	//----------------------------------------------------------------- Home Page
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomePage(ModelMap modelHolder) {

		return "homepage";
	}

	//----------------------------------------------------------------- Profile Page
	@RequestMapping(path="/users/profile", method=RequestMethod.GET)
	public String displayUserProfile(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "profilePage";
	}

	//----------------------------------------------------------------- Display Edit Profile Page
	@RequestMapping(path="/users/edit", method=RequestMethod.GET)
	public String displayEditProfileForm(ModelMap modelHolder, @ModelAttribute User user) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());

		}

		return "editProfile";
	}

	//----------------------------------------------------------------- Edit Profile
	@RequestMapping(path="/users/edit", method=RequestMethod.POST)
	public String editProfile(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash, HttpSession session) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/edit";
		}

		User currentUser = (User) session.getAttribute("currentUser");

		userDAO.updateProfile(currentUser.getUserName(), user);

		session.setAttribute("currentUser", userDAO.getUserByUserName(currentUser.getUserName()));

		return "redirect:/users/profile";
	}

	//----------------------------------------------------------------- Profile Pic Upload Page
	@RequestMapping(path="/users/upload", method=RequestMethod.GET)
	public String displayUploadImageForm(ModelMap modelHolder, @ModelAttribute User user) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());

		}

		return "uploadImage";
	}

	//----------------------------------------------------------------- Profile Pic Upload
	@RequestMapping(value="/users/upload", method = RequestMethod.POST)
	public String updateImage(@RequestParam(required = false) MultipartFile file, HttpSession session) {

		User currentUser = (User) session.getAttribute("currentUser");

		if (file != null && !file.isEmpty()) {
			try {
				// file name from username
				String defaultFileName = currentUser.getUserName();

				//save the file with the chosen name
				String fileName = uploadProvider.uploadFile(file, defaultFileName);

				//update the database with the saved file name
				userDAO.updateProfilePic(currentUser.getUserName(), fileName);

				session.setAttribute("currentUser", userDAO.getUserByUserName(currentUser.getUserName()));


				return "redirect:/users/profile";
			} catch (Throwable ex) {

			}
		} else {
			return "redirect:/users/upload";
		}

		return "redirect:/users/profile";
	}


}
