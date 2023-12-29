package com.example.managerxpressback.usertable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_table")
public class EUserTable {
    @Id
    private String idTable;
    private String idUser;
    private List<String> users;
    @NotBlank(message = "Table name cannot be blank")
    @Size(max = 20, message = "Table name must be at most 20 characters long")
    private String tableName;
    @NotEmpty(message = "Columns cannot be empty")
    @Size(min = 1, message = "At least one column must be specified")
    private Map<String, String> columns;

    public EUserTable(String tableName, Map<String, String> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EUserTable that = (EUserTable) o;

        // Add equality checks for relevant fields
        return idTable.equals(that.idTable) &&
                idUser.equals(that.idUser) &&
                tableName.equals(that.tableName) &&
                columns.equals(that.columns);
    }

    @Override
    public int hashCode() {
        // Generate hash code based on relevant fields
        return Objects.hash(idTable, idUser, tableName, columns);
    }

}
