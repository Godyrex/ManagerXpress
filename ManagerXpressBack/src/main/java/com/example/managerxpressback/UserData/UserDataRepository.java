package com.example.managerxpressback.UserData;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {
    List<UserData> findByIdTable(String idTable);

}
