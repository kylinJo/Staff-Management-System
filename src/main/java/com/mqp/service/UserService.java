package com.mqp.service;

import com.mqp.pojo.User;


public interface UserService {
    User selectPasswordByName(String userName, String password);
}
