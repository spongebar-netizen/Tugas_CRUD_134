package com.example.demo.service;



import com.example.demo.model.dto.UserAddRequest;
import com.example.demo.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto AddUser(UserAddRequest request);
    List<UserDto> getAllUser();
    UserDto getUserById(Integer id);
    UserDto UpdateUser(Integer id, UserAddRequest request);
    void DeleteUser(Integer id);
}