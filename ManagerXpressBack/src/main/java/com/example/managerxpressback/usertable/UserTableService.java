package com.example.managerxpressback.usertable;

import java.util.List;

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

