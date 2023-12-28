package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.UserData.UserData;
import com.example.managerxpressback.UserData.UserDataDTO;

import java.util.List;
import java.util.Optional;

public interface UserTableService {
    UserTable createUserTable(UserTable userTable);
    UserTable addUserToTable(String idUser,String idTable);
    UserTable removeUserFromTable(String idUser,String idTable);
    UserTableDTO getUserTableById(String tableId);
    List<UserTableDTO> getTablesByUser();
    List<UserTableDTO> getTablesByAddedUser();
    List<UserTableDTO> getAllUsersTables();
     UserTable validateUserTableOwnership(String tableId);
}

