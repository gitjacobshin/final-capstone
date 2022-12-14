package com.techelevator.controller;

import com.techelevator.model.dao.ProgressDAO;
import com.techelevator.model.dto.Progress;
import com.techelevator.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProgressController {

    @Autowired
    private ProgressDAO progressDAO;

    @Autowired
    public ProgressController(ProgressDAO progressDAO){
        this.progressDAO = progressDAO;
    }

    //----------------------------------------------------------------- Display Track Progress Form
    @RequestMapping(path="/users/progress", method=RequestMethod.GET)
    public String displayProgressForm(ModelMap modelHolder, HttpSession session) {
        if( ! modelHolder.containsAttribute("progress")) {
            modelHolder.addAttribute("progress", new Progress());
        }

        return "progressForm";
    }

    //----------------------------------------------------------------- Submit Track Progress Form
    @RequestMapping(path="/users/progress", method=RequestMethod.POST)
    public String submitProgress(@Valid @ModelAttribute Progress progress, BindingResult result, RedirectAttributes flash, HttpSession session) {

        if (result.hasErrors()) {
            flash.addFlashAttribute("progress", progress);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "progress", result);
            return "redirect:/users/progress";
        }

        User currentUser = (User) session.getAttribute("currentUser");

        progressDAO.addProgress(currentUser.getId(), progress.getWorkoutDate(), progress.getWorkoutType()
                                , progress.getWorkoutLength(), progress.getWeightAfter(), currentUser.getDesiredWeight());


        session.setAttribute("progressDates", progressDAO.getProgressDates(currentUser.getId()));
        session.setAttribute("progressWeights", progressDAO.getProgressWeights(currentUser.getId()));
        session.setAttribute("desiredWeights", progressDAO.getDesiredWeights(currentUser.getId()));
        session.setAttribute("progressTimes", progressDAO.getProgressTimes(currentUser.getId()));
        session.setAttribute("workoutTypes", progressDAO.getWorkoutType(currentUser.getId()));

        return "redirect:/users/profile";
    }
}
