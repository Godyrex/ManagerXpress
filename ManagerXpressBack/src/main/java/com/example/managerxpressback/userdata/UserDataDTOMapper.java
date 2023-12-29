package com.example.managerxpressback.userdata;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDataDTOMapper implements Function<EUserData, UserDataDTO> {
    @Override
    public UserDataDTO apply(EUserData eUserData) {
        return new UserDataDTO(
                eUserData.getData()
        );
    }
}
