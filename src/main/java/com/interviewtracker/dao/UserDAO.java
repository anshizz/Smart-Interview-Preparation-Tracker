package com.interviewtracker.dao;

import com.interviewtracker.model.User;
import java.util.List;

public interface UserDAO {
    boolean registerUser(User user);
    User getUserByEmail(String email);
    User getUserById(int userId);
    boolean updateUser(User user);
}
