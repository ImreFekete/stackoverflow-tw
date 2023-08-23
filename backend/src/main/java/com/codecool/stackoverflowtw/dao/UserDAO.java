package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;

public interface UserDAO {
    int addNewUser(NewUserDTO newUser);
    int authUser(NewUserDTO user);
}
