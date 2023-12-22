package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.UserData.UserData;

import java.util.List;
import java.util.Optional;

public interface UserTableService {
    UserTable createUserTable(UserTable userTable);

    Optional<UserTable> getUserTableById(String tableId);
    List<UserTable> getTablesByUser();
    List<UserTable> getAllUsersTables();

    UserData insertUserData(UserData userData);

    List<UserData> getUserDataByTableId(String tableId);
}

