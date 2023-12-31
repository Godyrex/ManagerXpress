package com.example.managerxpressback.userdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserDataDTO implements Serializable {

    String idTable;
    Map<String, Object> data;
}
