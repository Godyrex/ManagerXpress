package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.Security.Services.UserDetailsImpl;
import com.example.managerxpressback.Security.Services.UserDetailsServiceImpl;
import com.example.managerxpressback.UserData.UserData;
import com.example.managerxpressback.UserData.UserDataDTO;
import com.example.managerxpressback.UserData.UserDataDTOMapper;
import com.example.managerxpressback.UserData.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserTableServiceImpl implements UserTableService {

    private final UserTableRepository userTableRepository;
    private final UserTableDTOMapper userTableDTOMapper;

    @Override
    public UserTable validateUserTableOwnership(String tableId) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        Optional<UserTable> userTableOptional = userTableRepository.findById(tableId);

        if (userTableOptional.isPresent()) {
            UserTable userTable = userTableOptional.get();
            if (Objects.equals(userTable.getIdUser(), userDetails.getId())|| userTable.getUsers().contains(userDetails.getId())) {
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
    public UserTable addUserToTable(String idUser, String idTable) {
        UserTable userTable = validateUserTableOwnership(idTable);

        if (userTable != null) {
            if(userTable.getUsers() != null) {
                if(!userTable.getUsers().contains(idUser)) {
                    userTable.getUsers().add(idUser);
                    return userTableRepository.save(userTable);
                }else{
                    return null;
                }
            }else{
                List<String> users=new ArrayList<>();
                users.add(idUser);
                userTable.setUsers(users);
                return userTableRepository.save(userTable);
            }
        } else {
            return null;
        }
    }
    @Override
    public UserTable removeUserFromTable(String idUser, String idTable) {
        UserTable userTable = validateUserTableOwnership(idTable);

        if (userTable != null) {
            userTable.getUsers().remove(idUser);
            return userTableRepository.save(userTable);
        } else {
            return null;
        }
    }



    @Override
    public UserTableDTO getUserTableById(String tableId) {
        UserTable userTable = validateUserTableOwnership(tableId);
        if(userTable != null){
            UserTableDTO userTableDTO = userTableDTOMapper.apply(userTable);
            return userTableDTO;
        }else {
            return null;
        }
    }

    @Override
    public List<UserTableDTO> getTablesByUser() {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        return userTableRepository.findUserTablesByIdUser(userDetails.getId()).stream().map(userTableDTOMapper).collect(Collectors.toList());
    }
    @Override
    public List<UserTableDTO> getTablesByAddedUser() {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        return userTableRepository.findUserTablesByUsersContaining(userDetails.getId()).stream().map(userTableDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<UserTableDTO> getAllUsersTables() {
        return userTableRepository.findAll().stream().map(userTableDTOMapper).collect(Collectors.toList());
    }




}

