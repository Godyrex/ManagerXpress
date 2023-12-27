package com.example.managerxpressback.UserTable;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserTableDTOMapper implements Function<UserTable,UserTableDTO> {
    @Override
    public UserTableDTO apply(UserTable userTable) {
        return new UserTableDTO(
                userTable.getTableName(),
                userTable.getColumns()
        );
    }
}
