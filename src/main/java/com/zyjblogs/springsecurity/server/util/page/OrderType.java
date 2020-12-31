package com.zyjblogs.springsecurity.server.util.page;

import io.swagger.annotations.ApiModel;

@ApiModel(
        value = "com.example.mp_test.util.page.OrderType",
        description = "排序枚举类"
)
public enum OrderType {

    ASC,
    DESC,
    UNSORTED;

    private OrderType() {
    }
}
