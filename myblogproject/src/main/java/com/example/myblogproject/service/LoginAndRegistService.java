package com.example.myblogproject.service;

import com.example.myblogproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginAndRegistService {
    Boolean checkUsernameAndPassword(String username, String password);
    Boolean register(User user);


}
