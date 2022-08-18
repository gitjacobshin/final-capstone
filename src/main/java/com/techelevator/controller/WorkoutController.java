package com.techelevator.controller;

import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class WorkoutController {

    //----------------------------------------------------------------- Display Add Workout Form
    @RequestMapping(path="/workout/addWorkout", method= RequestMethod.GET)
    public String displayAddWorkoutForm(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("workout")) {
            modelHolder.addAttribute("workout", new Workout());
        }
        return "workoutForm";
    }

    //----------------------------------------------------------------- Edit Add Workout Form
    @RequestMapping(path="/workout/addWorkout", method=RequestMethod.POST)
    public String editWorkout(@Valid @ModelAttribute Workout workout, BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("workout", workout);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "workout", result);
            return "redirect:/workout/addWorkout";
        }

        return "redirect:/users/profile";
    }
}
