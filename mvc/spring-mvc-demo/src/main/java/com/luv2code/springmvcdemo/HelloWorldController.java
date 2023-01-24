package com.luv2code.springmvcdemo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloWorldController {
    
    // Show the initial form
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // Process the form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @RequestMapping("/processFormV2")
    public String letShoutDude(HttpServletRequest request, Model model){
        // Read request form html form
        String name = request.getParameter("name");

        // convert data to all caps
        // create the message
        String message = "Yo! " + name.toUpperCase();

        // add message to the model
        model.addAttribute("message", message);

        return "helloworld";
    }

    @RequestMapping("/processFormV3")
    public String processFormV3(@RequestParam("name") String name, Model model){
        // convert data to all caps
        // create the message
        String message = "Hey my friend! " + name.toUpperCase();

        // add message to the model
        model.addAttribute("message", message);

        return "helloworld";
    }
}
