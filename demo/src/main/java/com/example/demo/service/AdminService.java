package com.example.demo.service;

import com.example.demo.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    Admin save(Admin admin);
}
