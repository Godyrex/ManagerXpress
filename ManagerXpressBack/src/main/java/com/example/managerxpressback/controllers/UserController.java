package com.example.managerxpressback.controllers;

import com.example.managerxpressback.userdata.EUserData;
import com.example.managerxpressback.userdata.UserDataDTO;
import com.example.managerxpressback.userdata.UserDataService;
import com.example.managerxpressback.usertable.EUserTable;
import com.example.managerxpressback.usertable.UserTableDTO;
import com.example.managerxpressback.usertable.UserTableService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MODERATOR')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@AllArgsConstructor
public class UserController {


    private UserTableService userTableService;

    private UserDataService userDataService;

    @PostMapping("/create-table")
    public ResponseEntity<EUserTable> createUserTable(@Valid @RequestBody UserTableDTO userTableDTO) {
        EUserTable createdTable = userTableService.createUserTable(userTableDTO);
        return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
    }

    @PutMapping("/assign/{user}/To/{table}")
    public ResponseEntity<EUserTable> assignUserToTable(@PathVariable String user, @PathVariable String table) {
        EUserTable assignUser = userTableService.addUserToTable(user, table);
        return new ResponseEntity<>(assignUser, HttpStatus.OK);
    }

    @PutMapping("/remove/{user}/From/{table}")
    public ResponseEntity<EUserTable> removeUserFromTable(@PathVariable String user, @PathVariable String table) {
        EUserTable removeUser = userTableService.removeUserFromTable(user, table);
        return new ResponseEntity<>(removeUser, HttpStatus.OK);
    }

    @GetMapping("/get-table/{tableId}")
    public ResponseEntity<UserTableDTO> getUserTableById(@PathVariable String tableId) {
        UserTableDTO userTable = userTableService.getUserTableById(tableId);
        return new ResponseEntity<>(userTable, HttpStatus.OK);
    }

    @GetMapping("/get-assigned-user-tables")
    public ResponseEntity<List<UserTableDTO>> getAllAssignedUserTables() {
        List<UserTableDTO> userTables = userTableService.getTablesByAssginedUser();
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @GetMapping("/get-user-tables")
    public ResponseEntity<List<UserTableDTO>> getAllUserTables() {
        List<UserTableDTO> userTables = userTableService.getTablesByUser();
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }


    @PostMapping("/insert-data")
    public ResponseEntity<EUserData> insertUserData(@Valid @RequestBody UserDataDTO userDataDTO) {
        EUserData insertedData = userDataService.insertUserData(userDataDTO);
        return new ResponseEntity<>(insertedData, HttpStatus.CREATED);
    }

    @GetMapping("/get-data-by-table/{tableId}")
    public ResponseEntity<List<UserDataDTO>> getUserDataByTableName(@PathVariable String tableId) {
        List<UserDataDTO> userDataList = userDataService.getUserDataByTableId(tableId);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }

    @GetMapping("/search-data-by-table/{tableId}/{data}")
    public ResponseEntity<List<UserDataDTO>> searchUserDataByTableName(@PathVariable String tableId, @PathVariable String data) {
        List<UserDataDTO> userDataList = userDataService.searchUserDataByTableIdAndData(tableId, data);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }
}
