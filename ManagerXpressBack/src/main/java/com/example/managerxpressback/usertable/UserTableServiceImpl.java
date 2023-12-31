package com.example.managerxpressback.usertable;

import com.example.managerxpressback.exceptions.AssignedUserNotFoundException;
import com.example.managerxpressback.exceptions.InvalidTableException;
import com.example.managerxpressback.exceptions.TableNotFoundException;
import com.example.managerxpressback.exceptions.UserAlreadyAssignedException;
import com.example.managerxpressback.security.services.UserDetailsImpl;
import com.example.managerxpressback.security.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                throw new InvalidTableException("Table doesn't belong to the user");
            }
        } else {
            throw new TableNotFoundException("UserTable not found for ID: " + tableId);
        }
    }

    @Override
    public EUserTable createUserTable(CreateTableDTO createTableDTO) {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        EUserTable eUserTable = new EUserTable(createTableDTO.getTableName(),createTableDTO.getColumns());
        eUserTable.setIdUser(userDetails.getId());
        List<String> users = new ArrayList<>();
        eUserTable.setUsers(users);
        return userTableRepository.save(eUserTable);
    }

    @Override
    public boolean deleteUserTable(String idTable) {
        EUserTable eUserTable = validateUserTableOwnership(idTable);
       userTableRepository.delete(eUserTable);
            return true;
    }

    @Override
    public EUserTable addUserToTable(String idUser, String idTable) {
        EUserTable eUserTable = validateUserTableOwnership(idTable);

                if (!eUserTable.getUsers().contains(idUser)) {
                    eUserTable.getUsers().add(idUser);
                    return userTableRepository.save(eUserTable);
                } else {
                    throw new UserAlreadyAssignedException("User already assigned to "+eUserTable.getTableName());
                }
    }

    @Override
    public EUserTable removeUserFromTable(String idUser, String idTable) {
        EUserTable eUserTable = validateUserTableOwnership(idTable);
        if(eUserTable.getUsers().remove(idUser)) {
            return userTableRepository.save(eUserTable);
        }else{
            throw new AssignedUserNotFoundException("User already removed from "+eUserTable.getTableName());
        }
    }


    @Override
    public UserTableDTO getUserTableById(String tableId) {
        EUserTable eUserTable = validateUserTableOwnership(tableId);
            return userTableDTOMapper.apply(eUserTable);
    }

    @Override
    public List<UserTableDTO> getTablesByUser() {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        return userTableRepository.findUserTablesByIdUser(userDetails.getId()).stream().map(userTableDTOMapper).toList();
    }

    @Override
    public List<UserTableDTO> getTablesByAssginedUser() {
        UserDetailsImpl userDetails = UserDetailsServiceImpl.getCurrentUserDetails();
        return userTableRepository.findUserTablesByUsersContaining(userDetails.getId()).stream().map(userTableDTOMapper).toList();
    }

    @Override
    @Cacheable(value = "tables")
    public List<UserTableDTO> getAllUsersTables() {
        return userTableRepository.findAll().stream().map(userTableDTOMapper).toList();
    }


}

