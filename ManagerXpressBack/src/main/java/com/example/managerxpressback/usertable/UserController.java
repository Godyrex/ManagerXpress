package com.example.managerxpressback.usertable;

import com.example.managerxpressback.userdata.EUserData;
import com.example.managerxpressback.userdata.UserDataDTO;
import com.example.managerxpressback.userdata.UserDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@AllArgsConstructor
public class UserController {


    private UserTableService userTableService;

    private UserDataService userDataService;

    @PostMapping("/create-table")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<EUserTable> createUserTable(@Valid @RequestBody EUserTable eUserTable) {
        EUserTable createdTable = userTableService.createUserTable(eUserTable);
        return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
    }

    @PutMapping("/assigne/{user}/To/{table}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<EUserTable> assigneUserToTable(@PathVariable String user, @PathVariable String table) {
        EUserTable assigneUser = userTableService.addUserToTable(user, table);
        return new ResponseEntity<>(assigneUser, HttpStatus.CREATED);
    }

    @PutMapping("/remove/{user}/From/{table}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<EUserTable> removeUserFromTable(@PathVariable String user, @PathVariable String table) {
        EUserTable removeUser = userTableService.removeUserFromTable(user, table);
        return new ResponseEntity<>(removeUser, HttpStatus.CREATED);
    }

    @GetMapping("/get-table/{tableId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<UserTableDTO> getUserTableById(@PathVariable String tableId) {
        UserTableDTO userTable = userTableService.getUserTableById(tableId);
        return new ResponseEntity<>(userTable, HttpStatus.OK);
    }

    @GetMapping("/get-assigned-user-tables")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserTableDTO>> getAllAssignedUserTables() {
        List<UserTableDTO> userTables = userTableService.getTablesByAddedUser();
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @GetMapping("/get-user-tables")
    public ResponseEntity<List<UserTableDTO>> getAllUserTables() {
        List<UserTableDTO> userTables = userTableService.getTablesByUser();
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @GetMapping("/get-all-tables")
    public ResponseEntity<List<UserTableDTO>> getAllUsersTables() {
        List<UserTableDTO> userTables = userTableService.getAllUsersTables();
        return new ResponseEntity<>(userTables, HttpStatus.OK);
    }

    @PostMapping("/insert-data")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<EUserData> insertUserData(@Valid @RequestBody EUserData eUserData) {
        EUserData insertedData = userDataService.insertUserData(eUserData);
        return new ResponseEntity<>(insertedData, HttpStatus.CREATED);
    }

    @GetMapping("/get-data-by-table/{tableId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDataDTO>> getUserDataByTableName(@PathVariable String tableId) {
        List<UserDataDTO> userDataList = userDataService.getUserDataByTableId(tableId);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }

    @GetMapping("/search-data-by-table/{tableId}/{data}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDataDTO>> searchUserDataByTableName(@PathVariable String tableId, @PathVariable String data) {
        List<UserDataDTO> userDataList = userDataService.searchUserDataByTableIdAndData(tableId, data);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }
}
