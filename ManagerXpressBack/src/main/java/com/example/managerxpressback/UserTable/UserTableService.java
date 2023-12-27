package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.UserData.UserData;
import com.example.managerxpressback.UserData.UserDataDTO;

import java.util.List;
import java.util.Optional;

public interface UserTableService {
    UserTable createUserTable(UserTable userTable);

    Optional<UserTableDTO> getUserTableById(String tableId);
    List<UserTableDTO> getTablesByUser();
    List<UserTableDTO> getAllUsersTables();

    UserData insertUserData(UserData userData);

    List<UserDataDTO> getUserDataByTableId(String tableId);
    List<UserDataDTO> searchUserDataByTableIdAndData(String tableId,String searchData);
     UserTable validateUserTableOwnership(String tableId);
}

