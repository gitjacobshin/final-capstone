package com.techelevator.controller;

import com.techelevator.model.dto.User;
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
    @RequestMapping(path="/users/workout", method= RequestMethod.GET)
    public String displayAddWorkoutForm(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("user")) {
            modelHolder.addAttribute("user", new User());
        }
        return "addWorkout";
    }

    //----------------------------------------------------------------- Edit Add Workout Form
    @RequestMapping(path="/users/workout", method=RequestMethod.POST)
    public String editWorkout(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("user", user);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            return "redirect:/users/workout";
        }

        return "redirect:/users/profile";
    }
}
