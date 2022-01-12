package com.tmaexample.services.impl;

import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    UserDTO findById(long id);

    User findLoginById(long id);

    User insert(UserDTO user);

    User insertAdmin(UserDTO userDTO);

    boolean update(User user);
    void delete(long id);
}
