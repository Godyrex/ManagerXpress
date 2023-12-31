package com.example.managerxpressback.userdata;

import com.example.managerxpressback.exceptions.InvalidColumnsException;
import com.example.managerxpressback.usertable.EUserTable;
import com.example.managerxpressback.usertable.UserTableService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDataServiceImpl implements UserDataService {
    private final UserTableService userTableService;
    private final UserDataRepository userDataRepository;
    private final UserDataDTOMapper userDataDTOMapper;

    @Override
    @CacheEvict(value = "userData", key = "#userDataDTO.idTable()")
    public EUserData insertUserData(UserDataDTO userDataDTO) {
        EUserTable eUserTable = userTableService.validateUserTableOwnership(userDataDTO.getIdTable());
        EUserData eUserData = new EUserData(userDataDTO.getData(),userDataDTO.getIdTable());
        if (eUserData.isValid(eUserTable)) {
            return userDataRepository.save(eUserData);
        } else {
            throw new InvalidColumnsException("Invalid columns in UserData. Must match UserTable columns.");
        }
    }

    @Override
    @Cacheable(value = "userData", key = "#tableId")
    public List<UserDataDTO> getUserDataByTableId(String tableId) {
        userTableService.validateUserTableOwnership(tableId);
        return userDataRepository.findByIdTable(tableId).stream()
                .map(userDataDTOMapper)
                .toList();
    }

    @Override
    public List<UserDataDTO> searchUserDataByTableIdAndData(String tableId, String searchData) {
        userTableService.validateUserTableOwnership(tableId);
        List<EUserData> eUserDataList = userDataRepository.findByIdTable(tableId);

        // Filter the list based on the partial match of searchData in the "data" map values
        List<EUserData> filteredEUserData = eUserDataList.stream()
                .filter(userData -> userData.getData().values().stream()
                        .anyMatch(value -> value.toString().contains(searchData)))
                .toList();

        return filteredEUserData.stream().map(userDataDTOMapper).toList();
    }
}
