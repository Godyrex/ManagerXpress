package com.example.managerxpressback.usertable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTableRepository extends MongoRepository<UserTable, String> {
    List<UserTable> findUserTablesByIdUser(String IdUser);
    List<UserTable> findUserTablesByUsersContaining(String IdUser);
}
