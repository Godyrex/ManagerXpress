package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_table")
public class UserTable {
    @Id
    private String idTable;
    private String idUser;
    private String tableName;
    private Map<String, String> columns;
}
