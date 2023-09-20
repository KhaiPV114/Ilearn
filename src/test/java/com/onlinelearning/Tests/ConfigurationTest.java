package com.onlinelearning.Tests;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.Impl.DBContextImpl;
import com.onlinelearning.Utils.DotEnv;
import java.sql.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    
    @Order(1)
    @Test
    public void testDotEnv() {
        DotEnv dotEnv = new DotEnv();
        String envTestMessage = dotEnv.get("ENV_TEST");
        Assertions.assertEquals("Hello World!", envTestMessage);
    }
    
    @Order(2)
    @Test
    public void testDBContext() {
        DBContext dbContext = new DBContextImpl();
        Connection connection = dbContext.getConnection();
        Assertions.assertNotNull(connection);
    }
}
