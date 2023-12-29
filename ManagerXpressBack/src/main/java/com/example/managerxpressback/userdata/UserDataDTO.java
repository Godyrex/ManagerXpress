package com.example.managerxpressback.userdata;

import java.util.Map;

public record UserDataDTO(
        String idTable,
        Map<String, Object> data
) {


}
