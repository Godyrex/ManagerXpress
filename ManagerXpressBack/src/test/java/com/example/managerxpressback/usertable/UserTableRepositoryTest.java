package com.example.managerxpressback.usertable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
class UserTableRepositoryTest {
    @Autowired
    private UserTableRepository userTableRepository;
    private EUserTable eUserTable;

    @BeforeEach
    void setUp() {
        Map<String, String> columns = new HashMap<>();
        columns.put("Column1", "DataType1");
        columns.put("Column2", "DataType2");
        ArrayList<String> users = new ArrayList<>();
        users.add("id1");
        eUserTable = new EUserTable(
                "idtable",
                "iduser",
                users,
                "tablename",
                columns
        );
        userTableRepository.save(eUserTable);
    }
    @AfterEach
    void tearDown() {
        userTableRepository.deleteAll();
    }
    @Test
    void findUserTablesByIdUser() {
        List<EUserTable> expected = userTableRepository.findUserTablesByIdUser("iduser");
        assertThat(expected).contains(eUserTable);
    }

    @Test
    void findUserTablesByUsersContaining() {
        List<EUserTable> expected = userTableRepository.findUserTablesByUsersContaining("id1");
        assertThat(expected).contains(eUserTable);
    }
}