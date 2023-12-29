package com.example.managerxpressback.userdata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
@DataMongoTest
class UserDataRepositoryTest {
    @Autowired
private UserDataRepository userDataRepository;
    private EUserData eUserData;
    @BeforeEach
    void setUp() {
        Map<String, Object> Data = new HashMap<>();
        Data.put("name", "oussema");
        Data.put("lastname", "ouakad");

         eUserData = new EUserData(
                "idData",
                Data,
                "idTable"
        );
         userDataRepository.save(eUserData);
    }
    @AfterEach
    void tearDown() {
        userDataRepository.deleteAll();
    }
    @Test
    void findByIdTable() {
        List<EUserData> expected = userDataRepository.findByIdTable("idTable");
        assertThat(expected).contains(eUserData);
    }
}