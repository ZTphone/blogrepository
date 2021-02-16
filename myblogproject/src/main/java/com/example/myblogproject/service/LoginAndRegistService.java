package com.example.myblogproject.service;

import com.example.myblogproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginAndRegistService {
    Integer checkUsernameAndPassword(String username, String password);
    Boolean register(User user);


}
