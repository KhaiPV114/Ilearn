package com.onlinelearning.DAL;

import java.sql.Connection;

public interface DBContext {
    Connection getConnection();
}