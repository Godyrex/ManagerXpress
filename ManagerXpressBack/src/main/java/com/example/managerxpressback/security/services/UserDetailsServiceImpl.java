package com.example.managerxpressback.security.services;

import com.example.managerxpressback.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public static UserDetailsImpl getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EUser eUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(eUser);
    }
    @Transactional
    public void setUserRole(String username,ERole erole){
            EUser eUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
           Role role =  roleRepository.findByName(erole)
                .orElseThrow(() -> new NoSuchElementException("Error: Role is not found."));
           if(!eUser.getRoles().contains(role)){
               eUser.getRoles().add(role);
               userRepository.save(eUser);
           }else {
               throw new RoleAlreadyAssignedException("Role is already assigned to the user");
           }
    }
    @Transactional
    public void removeUserRole(String username,ERole erole){
        EUser eUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        Role role =  roleRepository.findByName(erole)
                .orElseThrow(() -> new NoSuchElementException("Error: Role is not found."));
        if(eUser.getRoles().contains(role)){
            eUser.getRoles().remove(role);
            userRepository.save(eUser);
        }else {
            throw new RoleAlreadyAssignedException("Role is already removed from the user");
        }
    }

}