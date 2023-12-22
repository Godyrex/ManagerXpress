package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.Security.Services.UserDetailsImpl;
import com.example.managerxpressback.Security.Services.UserDetailsServiceImpl;
import com.example.managerxpressback.UserData.UserData;
import com.example.managerxpressback.UserData.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserTableServiceImpl implements UserTableService {

    private final UserTableRepository userTableRepository;

    private final UserDataRepository userDataRepository;


    private UserTable validateUserTableOwnership(String tableId) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        Optional<UserTable> userTableOptional = userTableRepository.findById(tableId);

        if (userTableOptional.isPresent()) {
            UserTable userTable = userTableOptional.get();
            if (Objects.equals(userTable.getIdUser(), userDetails.getId())) {
                return userTable;
            } else {
                throw new IllegalArgumentException("Table doesn't belong to the user");
            }
        } else {
            throw new IllegalArgumentException("UserTable not found for ID: " + tableId);
        }
    }
    @Override
    public UserTable createUserTable(UserTable userTable) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        userTable.setIdUser(userDetails.getId());
        return userTableRepository.save(userTable);
    }


    @Override
    public Optional<UserTable> getUserTableById(String tableId) {
        UserTable userTable = validateUserTableOwnership(tableId);
        return Optional.of(userTable);
    }

    @Override
    public List<UserTable> getTablesByUser() {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        return userTableRepository.findUserTablesByIdUser(userDetails.getId());
    }

    @Override
    public List<UserTable> getAllUsersTables() {
        return userTableRepository.findAll();
    }

    @Override
    public UserData insertUserData(UserData userData) {
        UserTable userTable = validateUserTableOwnership(userData.getIdTable());

        if (userData.isValid(userTable)) {
            return userDataRepository.save(userData);
        } else {
            throw new IllegalArgumentException("Invalid columns in UserData. Must match UserTable columns.");
        }
    }

    @Override
    public List<UserData> getUserDataByTableId(String tableId) {
        validateUserTableOwnership(tableId);
        return userDataRepository.findByIdTable(tableId);

    }
}

