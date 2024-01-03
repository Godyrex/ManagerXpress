package com.example.managerxpressback.usertable;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserTableDTOMapper implements Function<EUserTable, UserTableDTO> {
    @Override
    public UserTableDTO apply(EUserTable eUserTable) {
        return new UserTableDTO(
                eUserTable.getIdTable(),
                eUserTable.getIdUser(),
                eUserTable.getTableName(),
                eUserTable.getColumns(),
                eUserTable.getUsers()
        );
    }
}
