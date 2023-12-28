package com.example.managerxpressback.UserTable;

import java.util.List;
import java.util.Map;

public record UserTableDTO(
        String user,
        String tableName,
        Map<String, String> columns,
        List<String> Users
        ) {
}
