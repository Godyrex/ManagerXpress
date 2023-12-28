package com.example.managerxpressback.UserData;

import com.example.managerxpressback.UserTable.UserTable;
import com.example.managerxpressback.UserTable.UserTableDTOMapper;
import com.example.managerxpressback.UserTable.UserTableRepository;
import com.example.managerxpressback.UserTable.UserTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserDataServiceImpl implements UserDataService{
    private final UserTableService userTableService;
    private final UserDataRepository userDataRepository;
    private final UserDataDTOMapper userDataDTOMapper;
    @Override
    public UserData insertUserData(UserData userData) {
        UserTable userTable = userTableService.validateUserTableOwnership(userData.getIdTable());

        if (userData.isValid(userTable)) {
            return userDataRepository.save(userData);
        } else {
            throw new IllegalArgumentException("Invalid columns in UserData. Must match UserTable columns.");
        }
    }

    @Override
    public List<UserDataDTO> getUserDataByTableId(String tableId) {
        userTableService.validateUserTableOwnership(tableId);
        return userDataRepository.findByIdTable(tableId).stream()
                .map(userDataDTOMapper)
                .collect(Collectors.toList());


    }

    @Override
    public List<UserDataDTO> searchUserDataByTableIdAndData(String tableId, String searchData) {
        userTableService.validateUserTableOwnership(tableId);
        List<UserData> userDataList = userDataRepository.findByIdTable(tableId);

        // Filter the list based on the partial match of searchData in the "data" map values
        List<UserData> filteredUserData = userDataList.stream()
                .filter(userData -> userData.getData().values().stream()
                        .anyMatch(value -> value.toString().contains(searchData)))
                .collect(Collectors.toList());

        return filteredUserData.stream().map(userDataDTOMapper).collect(Collectors.toList());
    }
}
