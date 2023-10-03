package com.onlinelearning.Enums;

public enum OrderStatus {
    NEW,        //When creating new order(check out)
    PENDING,    //After place order
    FAILED,     //For auto payment method
    SUCCESSFUL, //For auto payment method
    REJECTED,   //Manual check by admin
    DONE,       //Manual check by admin
    EXPIRED     //The order has not been paid after a while
}
