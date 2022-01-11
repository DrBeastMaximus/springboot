package com.tmaexample.services;

import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.User;
import com.tmaexample.repos.UserRepository;
import com.tmaexample.services.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<UserDTO> findAll(){
        List<User> users = userRepo.findAll();
        if(users.isEmpty())
            return null;
        List<UserDTO> rezult = new ArrayList<>();
        for (User user : users ){
            UserDTO usr = new UserDTO();
            usr.setId(user.getId());
            usr.setEmail(usr.getEmail());
            usr.setName(usr.getName());
            rezult.add(usr);
        }
        return rezult;
    }
    @Override
    public UserDTO findById(long id){
        User user = userRepo.getOne(id);
        if(user!=null)
            return null;
        UserDTO rezult = new UserDTO();
            rezult.setId(user.getId());
            rezult.setEmail(user.getEmail());
            rezult.setName(user.getName());
        return rezult;
    }

    @Override
    public User insert(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone("113");
        user.setCreated_at(new Date());
        user.getModified_at(new Date());
        return userRepo.save(user);
    }

    @Override
    public boolean update(User user) {
        if (userRepo.findById(user.getId()) == null)
            return false;
        User modifiedUser = userRepo.save(user);
        if (modifiedUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }
}
