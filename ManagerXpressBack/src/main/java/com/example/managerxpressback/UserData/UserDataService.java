package com.example.managerxpressback.UserData;

import java.util.List;

public interface UserDataService {
    UserData insertUserData(UserData userData);

    List<UserDataDTO> getUserDataByTableId(String tableId);
    List<UserDataDTO> searchUserDataByTableIdAndData(String tableId,String searchData);
}
