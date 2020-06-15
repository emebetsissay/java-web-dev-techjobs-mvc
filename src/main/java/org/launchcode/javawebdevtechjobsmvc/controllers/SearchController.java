package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    public String search( @RequestParam String mode, @RequestParam String term, Model model){
        ArrayList<Job> jobs;
        jobs = JobData.findByColumnAndValue(mode, term);
        model.addAttribute("type",mode );
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Condition: "+ columnChoices.get(mode) +"Term: "+ columnChoices.get(term));
        model.addAttribute("jobs",jobs);


        return "search.html";
    }

}
