package com.example.managerxpressback.userdata;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends MongoRepository<EUserData, String> {
    List<EUserData> findByIdTable(String idTable);

    @Query("{ 'idTable': ?0, 'data.?1': ?2 }")
    List<EUserData> findByIdTableAndDataContaining(String tableId, String key, Object value);


}
