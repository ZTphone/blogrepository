package com.example.myblogproject.service;

import com.example.myblogproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface EditPrivacyImformation {
    User getUserById(Integer id);
    Boolean modifyPassworod(Integer id,String newPassword);
    Boolean modifySex(Integer id,String sex);
    Boolean modifySignature(Integer id,String newSignature);
    Boolean modifyHeadImage();
    Boolean modifyByUser(User user);
    Boolean changeHeadImage(Integer id,String imageName);
}
