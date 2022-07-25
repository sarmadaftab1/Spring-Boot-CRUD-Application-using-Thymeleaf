package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public ModelAndView ShowPage(){
        ModelAndView mod = new ModelAndView();
        mod.setViewName("index.html");
        return mod;
    }

    @GetMapping("/customer1")
    public String showlist(Model model){
        List<Customer> customerDTOList= customerService.getAllCustomerSalman();
        model.addAttribute("listcustomer", customerDTOList);
        return "main";
    }

    @GetMapping("/customer1/post")
    public String newUser(Model model){
        model.addAttribute("customer", new Customer());
        return "add";
    }
    @PostMapping("/customer1/save")
    public String saveUser(Customer customer, RedirectAttributes ra){
        customerService.createCustomer(customer);
        ra.addFlashAttribute("message", "User Added");
        return "redirect:/customer1";
    }
    @GetMapping("/customer1/search")
    public String search(Model model){
        model.addAttribute("customer", new Customer());
        return "enterid";
    }
    @PostMapping("/search")
    public String doSearchEmployee(@ModelAttribute("employeeSearchFormData") Customer formData, Model model) {
        Customer customer = customerService.getCustomerById(formData.getCust_id());
        if (customer==null) {
            return "error";
        }
        else {
            model.addAttribute("customer", customer);
            return "enterid";
        }
    }

    @RequestMapping("/edit/{cust_id}")
    public String edit(@PathVariable("cust_id") long cust_id, Model model){
        Customer customer = customerService.getCustomerById(cust_id);
        model.addAttribute("customer", customer);
        return "update";
    }

    @RequestMapping("/delete/{cust_id}")
    public String delete(@PathVariable("cust_id") long cust_id, RedirectAttributes attributes){
        customerService.deleteCustomer(cust_id);
        attributes.addFlashAttribute("success", "The student has deleted!");
        return "redirect:/customer1";
    }
}

