package com.techelevator.controller;

import com.techelevator.model.dao.ExerciseDAO;
import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ExerciseController {

    private ExerciseDAO exerciseDAO;

    @Autowired
    public ExerciseController(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }


    //----------------------------------------------------------------- Display Custom Exercise Form
    @RequestMapping(path="/users/custom-exercise", method= RequestMethod.GET)
    public String displayCustomExerciseForm(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("user")) {
            modelHolder.addAttribute("user", new User());
        }
        return "customExercise";
    }

    //----------------------------------------------------------------- Edit Custom Exercise Form
    @RequestMapping(path="/users/custom-exercise", method=RequestMethod.POST)
    public String editCustomExercise(@Valid @ModelAttribute Exercise exercise, BindingResult result, RedirectAttributes flash) {
//        if (result.hasErrors()) {
//            flash.addFlashAttribute("user", user);
//            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
//            return "redirect:/users/workout/custom-exercise";
//        }

        // Model after: userDAO.saveUser(user.getUserName(), user.getPassword());
        //exerciseDAO.saveExercise();
        return "redirect:/users/workout";
    }
}
