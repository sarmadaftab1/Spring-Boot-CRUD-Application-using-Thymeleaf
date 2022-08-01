package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String showuserlist(Model model){
        List<User> userlist= userService.getAllUsers();
        model.addAttribute("listuser", userlist);
        return "usersdetail";
    }

    @GetMapping("/user/search")
    public String search(Model model){
        model.addAttribute("user", new User());
        return "userSearch";
    }
    @PostMapping("user1/search")
    public String doSearchEmployee(@ModelAttribute("employeeSearchFormData") User formData, Model model) {
        User user = userService.getUserById(formData.getId());
        if (user==null) {
            return "error1";
        }
        else {
            model.addAttribute("user", user);
            return "userSearch";
        }
    }

    @RequestMapping("/deleteuser/{id}")
    public String delete(@PathVariable("id") long cust_id, RedirectAttributes attributes){
        userService.deleteUser(cust_id);
        attributes.addFlashAttribute("success", "The User has deleted!");
        return "redirect:/user";
    }
}
