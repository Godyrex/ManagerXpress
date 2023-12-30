package com.example.managerxpressback.security.services;

import com.example.managerxpressback.exceptions.RoleAlreadyAssignedException;
import com.example.managerxpressback.exceptions.UserNotAuthenticatedException;
import com.example.managerxpressback.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    private static final String USER_NOT_FOUND_ERROR = "User Not Found with username: ";
    private static final String ROLE_NOT_FOUND = "Error: Role is not found.";
    private static final String ROLE_ALREADY_ASSIGNED = "Role is already assigned to the user";
    public static UserDetailsImpl getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("User is not authenticated");
        }
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EUser eUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR + username));

        return UserDetailsImpl.build(eUser);
    }
    @Transactional
    public void setUserRole(String username,ERole erole){
            EUser eUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR + username));
           Role role =  roleRepository.findByName(erole)
                .orElseThrow(() -> new NoSuchElementException(ROLE_NOT_FOUND));
           if(!eUser.getRoles().contains(role)){
               eUser.getRoles().add(role);
               userRepository.save(eUser);
           }else {
               throw new RoleAlreadyAssignedException(ROLE_ALREADY_ASSIGNED);
           }
    }
    @Transactional
    public void removeUserRole(String username,ERole erole){
        EUser eUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR + username));
        Role role =  roleRepository.findByName(erole)
                .orElseThrow(() -> new NoSuchElementException(ROLE_NOT_FOUND));
        if(eUser.getRoles().contains(role)){
            eUser.getRoles().remove(role);
            userRepository.save(eUser);
        }else {
            throw new RoleAlreadyAssignedException(ROLE_ALREADY_ASSIGNED);
        }
    }

}