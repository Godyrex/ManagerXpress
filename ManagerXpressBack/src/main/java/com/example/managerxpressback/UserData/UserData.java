package com.example.managerxpressback.UserData;

import com.example.managerxpressback.UserTable.UserTable;
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
@Document(collection = "user_data")
public class UserData {
    @Id
    private String idData;
    private Map<String, Object> data;
    private String idTable;
    public boolean isValid(UserTable userTable) {
        return userTable.getColumns().keySet().containsAll(data.keySet());
    }
}
