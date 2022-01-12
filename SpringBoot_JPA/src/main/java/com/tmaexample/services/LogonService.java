package com.tmaexample.services;

import com.tmaexample.entities.Role;
import com.tmaexample.entities.User;
import com.tmaexample.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class LogonService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user!=null){
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("User not existed!");
    }
}
