package com.example.managerxpressback.userdata;

import java.util.List;

public interface UserDataService {
    EUserData insertUserData(UserDataDTO userDataDTO);

    List<UserDataDTO> getUserDataByTableId(String tableId);

    List<UserDataDTO> searchUserDataByTableIdAndData(String tableId, String searchData);
}
