package com.example.managerxpressback.controllers;

import com.example.managerxpressback.security.services.RoleAlreadyAssignedException;
import com.example.managerxpressback.security.services.UserDetailsServiceImpl;
import com.example.managerxpressback.user.ERole;
import com.example.managerxpressback.user.Role;
import com.example.managerxpressback.usertable.EUserTable;
import com.example.managerxpressback.usertable.UserTableDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    UserDetailsServiceImpl userDetailsService;
    @PutMapping("/setRoleAdmin/{user}")
    public ResponseEntity<String> setUserRoleAdmin(@PathVariable String user) {
        return setUserRole(user, ERole.ROLE_ADMIN, "Role added successfully");
    }

    @PutMapping("/setRoleModerator/{user}")
    public ResponseEntity<String> setUserRoleModerator(@PathVariable String user) {
        return setUserRole(user, ERole.ROLE_MODERATOR, "Role added successfully");
    }

    @PutMapping("/removeRoleAdmin/{user}")
    public ResponseEntity<String> removeUserRoleAdmin(@PathVariable String user) {
        return removeUserRole(user, ERole.ROLE_ADMIN, "Role removed successfully");
    }

    @PutMapping("/removeRoleModerator/{user}")
    public ResponseEntity<String> removeUserRoleModerator(@PathVariable String user) {
        return removeUserRole(user, ERole.ROLE_MODERATOR, "Role removed successfully");
    }

    private ResponseEntity<String> setUserRole(String user, ERole role, String successMessage) {
        try {
            userDetailsService.setUserRole(user, role);
            return ResponseEntity.ok(successMessage);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Role is not found");
        } catch (RoleAlreadyAssignedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role is already assigned to the user");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    private ResponseEntity<String> removeUserRole(String user, ERole role, String successMessage) {
        try {
            userDetailsService.removeUserRole(user, role);
            return ResponseEntity.ok(successMessage);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Role is not found");
        } catch (RoleAlreadyAssignedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role is already removed from the user");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

}
