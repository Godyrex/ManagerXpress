package com.example.managerxpressback.UserTable;

import java.util.Map;

public record UserTableDTO(
        String tableName,
        Map<String, String> columns
        ) {
}
