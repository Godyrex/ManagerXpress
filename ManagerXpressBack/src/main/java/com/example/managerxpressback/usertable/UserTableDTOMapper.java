package com.example.managerxpressback.usertable;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserTableDTOMapper implements Function<UserTable,UserTableDTO> {
    @Override
    public UserTableDTO apply(UserTable userTable) {
        return new UserTableDTO(
                userTable.getIdUser(),
                userTable.getTableName(),
                userTable.getColumns(),
                userTable.getUsers()
        );
    }
}
