package com.example.managerxpressback.UserTable;

import com.example.managerxpressback.UserData.UserData;
import com.example.managerxpressback.UserData.UserDataDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class UserController {

    @Autowired
    private UserTableService userTableService;

    @PostMapping("/create-table")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<UserTable> createUserTable(@Valid @RequestBody UserTable userTable) {
        UserTable createdTable = userTableService.createUserTable(userTable);
        return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
    }

    @GetMapping("/get-table/{tableId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<UserTableDTO> getUserTableById(@PathVariable String tableId) {
        Optional<UserTableDTO> userTable = userTableService.getUserTableById(tableId);
        return userTable.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<UserData> insertUserData(@Valid @RequestBody UserData userData) {
        UserData insertedData = userTableService.insertUserData(userData);
        return new ResponseEntity<>(insertedData, HttpStatus.CREATED);
    }

    @GetMapping("/get-data-by-table/{tableId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDataDTO>> getUserDataByTableName(@PathVariable String tableId) {
        List<UserDataDTO> userDataList = userTableService.getUserDataByTableId(tableId);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }
    @GetMapping("/search-data-by-table/{tableId}/{data}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDataDTO>> searchUserDataByTableName(@PathVariable String tableId, @PathVariable String data) {
        List<UserDataDTO> userDataList = userTableService.searchUserDataByTableIdAndData(tableId,data);
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }
}
