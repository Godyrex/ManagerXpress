package com.example.managerxpressback.user;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private final ERole name;



    public Role(ERole name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
