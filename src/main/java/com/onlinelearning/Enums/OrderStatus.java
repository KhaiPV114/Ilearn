package com.onlinelearning.Enums;

public enum OrderStatus {
    NEW,
    UNPAID,     //When creating new order(check out)
    FAILED,     //For auto payment method
    SUCCESSFUL, //For auto payment method
}
