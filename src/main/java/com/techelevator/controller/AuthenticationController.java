package com.techelevator.controller;

import javax.servlet.http.HttpSession;

import com.techelevator.model.dao.ProgressDAO;
import com.techelevator.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.dao.UserDAO;

@Controller
public class AuthenticationController {

	private UserDAO userDAO;
	private ProgressDAO progressDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO, ProgressDAO progressDAO) {
		this.userDAO = userDAO;
		this.progressDAO = progressDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						@RequestParam(required=false) String destination,
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));

			User currentUser = (User) session.getAttribute("currentUser");

			session.setAttribute("progressDates", progressDAO.getProgressDates(currentUser.getId()));
			session.setAttribute("progressWeights", progressDAO.getProgressWeights(currentUser.getId()));
			session.setAttribute("desiredWeights", progressDAO.getDesiredWeights(currentUser.getId()));
			session.setAttribute("progressTimes", progressDAO.getProgressTimes(currentUser.getId()));
			session.setAttribute("workoutTypes", progressDAO.getWorkoutType(currentUser.getId()));
			
			if(destination != null && ! destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				return "redirect:/users/profile";
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/";
	}
}
