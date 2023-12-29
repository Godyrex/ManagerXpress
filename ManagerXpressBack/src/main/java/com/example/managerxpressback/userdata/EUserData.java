package com.example.managerxpressback.userdata;

import com.example.managerxpressback.usertable.EUserTable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_data")
public class EUserData {
    @Id
    private String idData;
    @NotEmpty(message = "Data cannot be empty")
    private Map<String, Object> data;
    private String idTable;

    public boolean isValid(EUserTable eUserTable) {
        return eUserTable.getColumns().keySet().containsAll(data.keySet());
    }

    public EUserData(Map<String, Object> data, String idTable) {
        this.data = data;
        this.idTable = idTable;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EUserData that = (EUserData) o;
        return Objects.equals(idData, that.idData) &&
                Objects.equals(data, that.data) &&
                Objects.equals(idTable, that.idTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idData, data, idTable);
    }

}
