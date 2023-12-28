package com.example.managerxpressback.userdata;

import com.example.managerxpressback.usertable.UserTable;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Data cannot be empty")
    private Map<String, Object> data;
    private String idTable;
    public boolean isValid(UserTable userTable) {
        return userTable.getColumns().keySet().containsAll(data.keySet());
    }
}
