package com.techelevator.controller;

import com.techelevator.model.dao.ExerciseDAO;
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
import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseDAO exerciseDAO;
    @Autowired
    private WorkoutDAO workoutDAO;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    public ExerciseController(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }


    //----------------------------------------------------------------- Display Exercise Form
    @RequestMapping(path="/users/exerciseForm", method= RequestMethod.GET)
    public String displayExerciseForm(ModelMap modelHolder, HttpSession session) {
        if( ! modelHolder.containsAttribute("workout")) {
            modelHolder.addAttribute("workout", new Workout());
        }

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        List<Exercise> exercises = workoutDAO.showExercises(currentWorkout.getWorkoutName());

        session.setAttribute("exercises", exercises);

        return "exerciseForm";
    }

    //----------------------------------------------------------------- POST display Exercise Form
    @RequestMapping(path="/users/exerciseForm", method=RequestMethod.POST)
    public String finalizeWorkout(@Valid @ModelAttribute Exercise exercise, BindingResult result, RedirectAttributes flash, HttpSession session) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("exercise", exercise);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            return "redirect:/users/exerciseForm";
        }

        User currentUser = (User) session.getAttribute("currentUser");

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        workoutDAO.updateWorkout(currentUser.getUserName(), currentWorkout);

        workoutDAO.updateTotalCalories(currentUser.getId(), currentWorkout);

        session.setAttribute("currentWorkout", workoutDAO.getWorkoutByWorkoutName(currentUser.getUserName(), currentWorkout.getWorkoutName()));

        session.setAttribute("workouts", userDAO.showWorkouts(currentUser.getUserName()));

        return "redirect:/users/profile";
    }

    //----------------------------------------------------------------- Display Custom Exercise Form
    @RequestMapping(path="/users/custom-exercise", method= RequestMethod.GET)
    public String displayCustomExerciseForm(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("user")) {
            modelHolder.addAttribute("user", new User());
        }
        return "customExercise";
    }

    //----------------------------------------------------------------- Create Custom Exercise Form
    @RequestMapping(path="/users/custom-exercise", method=RequestMethod.POST)
    public String createCustomExercise(@Valid @ModelAttribute Exercise exercise, BindingResult result, RedirectAttributes flash, HttpSession session) {

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        boolean isExerciseNameAvailable = exerciseDAO.isExerciseNameAvailable(exercise.getExerciseName(), currentWorkout.getWorkoutName());
        if(!isExerciseNameAvailable) {
            FieldError error = new FieldError("exercise", "exerciseName","Exercise name is already used.");
            result.addError(error);
        }

        if (result.hasErrors()) {
            flash.addFlashAttribute("exercise", exercise);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            return "redirect:/users/workout/custom-exercise";
        }

        currentWorkout = (Workout) session.getAttribute("currentWorkout");

        exerciseDAO.createExercise(currentWorkout, exercise);

        return "redirect:/users/exerciseForm";
    }

    //----------------------------------------------------------------- Edit Exercise Form
    @RequestMapping(path="/users/custom-exercise/edit/{exerciseName}", method= RequestMethod.GET)
    public String editExerciseForm(ModelMap modelHolder, @ModelAttribute Exercise exercise, HttpSession session) {
        if( ! modelHolder.containsAttribute("exercise")) {
            modelHolder.addAttribute("exercise", new Exercise());
        }

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        Exercise selectedExercise = (Exercise) exerciseDAO.getExerciseByExerciseName(currentWorkout.getWorkoutName(), exercise.getExerciseName());

        session.setAttribute("currentExercise", selectedExercise);

        return "editExercise";
    }

    //----------------------------------------------------------------- POST edit Exercise Form
    @RequestMapping(path="/users/custom-exercise/edit/{id}", method=RequestMethod.POST)
    public String editExercise(@Valid @ModelAttribute Exercise exercise, @PathVariable Integer id, BindingResult result, RedirectAttributes flash, HttpSession session) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("exercise", exercise);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            return "redirect:/users/custom-exercise/edit/{id}";
        }

        Workout currentWorkout = (Workout) session.getAttribute("currentWorkout");

        exercise.setId(id);

        exerciseDAO.updateExercise(currentWorkout, exercise);

        session.setAttribute("currentExercise", exerciseDAO.getExerciseByExerciseName(currentWorkout.getWorkoutName(), exercise.getExerciseName()));

        return "redirect:/users/exerciseForm";
    }


    //----------------------------------------------------------------- GET DELETE Exercise Form
    @RequestMapping(path="/users/delete-exercise/{id}", method= RequestMethod.GET)
    public String deleteExercise(ModelMap modelHolder, @ModelAttribute Exercise exercise, @PathVariable Integer id, HttpSession session) {
        if( ! modelHolder.containsAttribute("exercise")) {
            modelHolder.addAttribute("exercise", new Exercise());
        }

        exercise.setId(id);

        exerciseDAO.deleteExercise(exercise);

        session.setAttribute("currentExercise", null);

        return "redirect:/users/exerciseForm";
    }


}
