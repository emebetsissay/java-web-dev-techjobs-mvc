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
    @RequestMapping(value = "results")
    public String displaySearchResult( @RequestParam String searchType, @RequestParam String searchTerm, Model model){

        ArrayList<Job> jobs;
        if(searchTerm.equals("") || searchTerm == null || searchTerm.toLowerCase().equals("all")){
            jobs = JobData.findAll();

        } else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("type",searchType );
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Condition: "+ columnChoices.get(searchType) +"Term: "+ columnChoices.get(searchTerm));
        model.addAttribute("jobs",jobs);


        return "search";
    }

}
