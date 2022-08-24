package com.techelevator.controller;

import com.techelevator.model.dao.UserDAO;
import com.techelevator.model.dao.WorkoutDAO;
import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutDAO workoutDAO;
    @Autowired
    private UserDAO userDAO;

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

    //----------------------------------------------------------------- GET DELETE Workout Form
    @RequestMapping(path="/users/delete-workout/{id}", method= RequestMethod.GET)
    public String deleteWorkout(ModelMap modelHolder, @ModelAttribute Workout workout, @PathVariable Integer id, HttpSession session) {
        if( ! modelHolder.containsAttribute("workout")) {
            modelHolder.addAttribute("workout", new Workout());
        }

        workout.setId(id);

        workoutDAO.deleteWorkout(workout);

        session.setAttribute("currentWorkout", null);

        User currentUser = (User) session.getAttribute("currentUser");

        session.setAttribute("workouts", userDAO.showWorkouts(currentUser.getUserName()));

        return "redirect:/users/profile";
    }

    //----------------------------------------------------------------- Edit Exercise Form
    @RequestMapping(path="/users/workout/view/{workoutName}", method= RequestMethod.GET)
    public String viewWorkoutForm(ModelMap modelHolder, @ModelAttribute Workout workout, HttpSession session) {
        if( ! modelHolder.containsAttribute("workout")) {
            modelHolder.addAttribute("workout", new Workout());
        }

        User currentUser = (User) session.getAttribute("currentUser");

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        session.setAttribute("workout", workoutDAO.getWorkoutByWorkoutName(currentUser.getUserName(),
                currentWorkout.getWorkoutName()));

        return "viewWorkout";
    }

//    //----------------------------------------------------------------- POST edit Exercise Form
//    @RequestMapping(path="/users/view/{workoutName}", method=RequestMethod.POST)
//    public String editWorkout(@Valid @ModelAttribute Workout workout, @PathVariable Integer id, BindingResult result, RedirectAttributes flash, HttpSession session) {
//        if (result.hasErrors()) {
//            flash.addFlashAttribute("workout", workout);
//            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
//            return "redirect:/users/custom-exercise/edit/{id}";
//        }
//
//        User currentUser = (User) session.getAttribute("currentUser");
//
//        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");
//
//        session.setAttribute("workout", workoutDAO.getWorkoutByWorkoutName(currentUser.getUserName(),
//                currentWorkout.getWorkoutName()));
//
//        session.setAttribute("exercises", workoutDAO.showExercises(currentWorkout.getWorkoutName()));
//
//        return "redirect:/users/profile";
//    }
}
