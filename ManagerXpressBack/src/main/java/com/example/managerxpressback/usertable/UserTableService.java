package com.example.managerxpressback.usertable;

import java.util.List;

public interface UserTableService {
    EUserTable createUserTable(UserTableDTO userTableDTO);

    EUserTable addUserToTable(String idUser, String idTable);

    EUserTable removeUserFromTable(String idUser, String idTable);

    UserTableDTO getUserTableById(String tableId);
    List<UserTableDTO> getTablesByUser();

    List<UserTableDTO> getTablesByAssginedUser();
    List<UserTableDTO> getAllUsersTables();

    EUserTable validateUserTableOwnership(String tableId);
}

