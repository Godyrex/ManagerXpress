package com.example.managerxpressback.usertable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTableRepository extends MongoRepository<EUserTable, String> {
    List<EUserTable> findUserTablesByIdUser(String idUser);

    List<EUserTable> findUserTablesByUsersContaining(String idUser);
}
