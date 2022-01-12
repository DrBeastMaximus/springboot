package com.tmaexample.services;

import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.Role;
import com.tmaexample.entities.User;
import com.tmaexample.repos.RoleRepository;
import com.tmaexample.repos.UserRepository;
import com.tmaexample.services.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    public String randomString(){
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    @Override
    public List<UserDTO> findAll(){
        List<User> users = userRepo.findAll();
        if(users.isEmpty())
            return null;
        List<UserDTO> rezult = new ArrayList<>();
        for (User user : users ){
            UserDTO usr = new UserDTO();
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
            rezult.setEmail(user.getEmail());
            rezult.setName(user.getName());
        return rezult;
    }

    @Override
    public User findLoginById(long id){
        User user = userRepo.getOne(id);
        if(user!=null)
            return null;
        return user;
    }

    @Override
    public User insert(UserDTO userDTO) {
        User user = new User();
        Role userRole = roleRepo.getOne(Long.valueOf(2));
        Set<Role> roles = Stream.of(userRole)
                .collect(Collectors.toCollection(HashSet::new));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone("113");
        user.setCreated_at(new Date());
        user.setModified_at(new Date());
        user.setPassword(randomString());
        user.setRoles(roles);
        return userRepo.save(user);
    }
    @Override
    public User insertAdmin(UserDTO userDTO) {
        User user = new User();
        Role userRole = roleRepo.getOne(Long.valueOf(2));
        Set<Role> roles = Stream.of(userRole)
                .collect(Collectors.toCollection(HashSet::new));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone("113");
        user.setCreated_at(new Date());
        user.setModified_at(new Date());
        user.setPassword(randomString());
        user.setRoles(roles);
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
