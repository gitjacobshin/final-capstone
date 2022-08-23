package com.techelevator.controller;

import com.techelevator.model.dao.UserDAO;
import com.techelevator.model.dao.WorkoutDAO;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutDAO workoutDAO;
    //@Autowired
    //private UserDAO userDAO;

    //----------------------------------------------------------------- Display Add Workout Form
    @RequestMapping(path="/users/workout/newWorkoutForm", method= RequestMethod.GET)
    public String displayAddWorkoutForm(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("workout")) {
            modelHolder.addAttribute("workout", new Workout());
        }
        return "workoutForm";
    }

    @RequestMapping(path="/users/workout/newWorkoutForm", method=RequestMethod.POST)
    public String createWorkout(@Valid @ModelAttribute Workout workout, BindingResult result, RedirectAttributes flash, HttpSession session) {

        User currentUser = (User) session.getAttribute("currentUser");

        boolean isWorkoutNameAvailable = workoutDAO.isWorkoutAvailable(workout.getWorkoutName(), currentUser.getUserName());
        if(!isWorkoutNameAvailable) {
            FieldError error = new FieldError("workout", "workoutName","Workout name is already used.");
            result.addError(error);
        }
        if(result.hasErrors()) {
            flash.addFlashAttribute("workout", workout);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "workout", result);
            return "redirect:/users/workout/newWorkoutForm";
        }

        workoutDAO.createWorkout(currentUser, workout);

        session.setAttribute("currentWorkout", workoutDAO.getWorkoutByWorkoutName(currentUser.getUserName(), workout.getWorkoutName()));

        return "redirect:/users/exerciseForm";
    }
}
