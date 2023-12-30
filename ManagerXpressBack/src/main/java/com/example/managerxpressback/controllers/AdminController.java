package com.example.managerxpressback.controllers;

import com.example.managerxpressback.security.services.UserDetailsServiceImpl;
import com.example.managerxpressback.user.ERole;
import com.example.managerxpressback.usertable.UserTableDTO;
import com.example.managerxpressback.usertable.UserTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    UserDetailsServiceImpl userDetailsService;
    UserTableService userTableService;
    @PutMapping("/setRoleAdmin/{user}")
    public void setUserRoleAdmin(@PathVariable String user) {
            userDetailsService.setUserRole(user, ERole.ROLE_ADMIN);
    }

    @PutMapping("/setRoleModerator/{user}")
    public void setUserRoleModerator(@PathVariable String user) {
            userDetailsService.setUserRole(user, ERole.ROLE_MODERATOR);
    }

    @PutMapping("/removeRoleAdmin/{user}")
    public void removeUserRoleAdmin(@PathVariable String user) {
            userDetailsService.removeUserRole(user, ERole.ROLE_ADMIN);
    }

    @PutMapping("/removeRoleModerator/{user}")
    public void removeUserRoleModerator(@PathVariable String user) {
            userDetailsService.removeUserRole(user, ERole.ROLE_MODERATOR);
    }
    @GetMapping("/get-all-tables")
    public ResponseEntity<List<UserTableDTO>> getAllUsersTables() {
            List<UserTableDTO> userTables = userTableService.getAllUsersTables();
            return new ResponseEntity<>(userTables, HttpStatus.OK);
    }


}
