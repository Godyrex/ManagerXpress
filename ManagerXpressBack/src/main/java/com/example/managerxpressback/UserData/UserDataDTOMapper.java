package com.example.managerxpressback.UserData;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDataDTOMapper implements Function<UserData,UserDataDTO> {
    @Override
    public UserDataDTO apply(UserData userData) {
        return new UserDataDTO(
                userData.getData()
        );
    }
}
