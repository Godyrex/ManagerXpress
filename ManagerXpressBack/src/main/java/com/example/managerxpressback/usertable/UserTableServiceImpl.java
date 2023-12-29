package com.example.managerxpressback.usertable;

import com.example.managerxpressback.security.services.UserDetailsImpl;
import com.example.managerxpressback.security.services.UserDetailsServiceImpl;
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
    public EUserTable validateUserTableOwnership(String tableId) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        Optional<EUserTable> userTableOptional = userTableRepository.findById(tableId);

        if (userTableOptional.isPresent()) {
            EUserTable eUserTable = userTableOptional.get();
            if (Objects.equals(eUserTable.getIdUser(), userDetails.getId()) || eUserTable.getUsers().contains(userDetails.getId())) {
                return eUserTable;
            } else {
                throw new IllegalArgumentException("Table doesn't belong to the user");
            }
        } else {
            throw new IllegalArgumentException("UserTable not found for ID: " + tableId);
        }
    }

    @Override
    public EUserTable createUserTable(EUserTable eUserTable) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        eUserTable.setIdUser(userDetails.getId());
        return userTableRepository.save(eUserTable);
    }

    @Override
    public EUserTable addUserToTable(String idUser, String idTable) {
        EUserTable eUserTable = validateUserTableOwnership(idTable);

        if (eUserTable != null) {
            if (eUserTable.getUsers() != null) {
                if (!eUserTable.getUsers().contains(idUser)) {
                    eUserTable.getUsers().add(idUser);
                    return userTableRepository.save(eUserTable);
                } else {
                    return null;
                }
            } else {
                List<String> users = new ArrayList<>();
                users.add(idUser);
                eUserTable.setUsers(users);
                return userTableRepository.save(eUserTable);
            }
        } else {
            return null;
        }
    }

    @Override
    public EUserTable removeUserFromTable(String idUser, String idTable) {
        EUserTable eUserTable = validateUserTableOwnership(idTable);

        if (eUserTable != null) {
            eUserTable.getUsers().remove(idUser);
            return userTableRepository.save(eUserTable);
        } else {
            return null;
        }
    }


    @Override
    public UserTableDTO getUserTableById(String tableId) {
        EUserTable eUserTable = validateUserTableOwnership(tableId);
        if (eUserTable != null) {
            return userTableDTOMapper.apply(eUserTable);
        } else {
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

