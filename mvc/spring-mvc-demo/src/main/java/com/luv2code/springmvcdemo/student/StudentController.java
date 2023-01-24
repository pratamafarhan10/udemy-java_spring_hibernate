package com.luv2code.springmvcdemo.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    @RequestMapping("/showForm")
    public String showForm(){
        return "student/student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student){
        return "student/confirmation";
    }
}
