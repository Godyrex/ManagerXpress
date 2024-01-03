package com.example.managerxpressback.usertable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CreateTableDTO implements Serializable {
    String tableName;
    Map<String, String> columns;
}
