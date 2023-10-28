package com.onlinelearning.Tests;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.Impl.DBContextImpl;
import com.onlinelearning.Utils.DotEnv;
import com.onlinelearning.Utils.Encryption;
import com.onlinelearning.Utils.Impl.AESEncryptionImpl;
import java.sql.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    
    @Order(1)
    @Test
    public void testDotEnv() {
        String envTestMessage = DotEnv.get("ENV_TEST");
        Assertions.assertEquals("Hello World!", envTestMessage);
    }
    
    @Order(2)
    @Test
    public void testDBContext() {
        DBContext dbContext = new DBContextImpl();
        Connection connection = dbContext.getConnection();
        Assertions.assertNotNull(connection);
    }
    
    @Order(3)
    @Test
    public void testAESPrivateKey() {
        String originalString = "Chac ai do se ve";
        Encryption aes = new AESEncryptionImpl();
        String encrypted = "rL9W+qfxxiBepJRsMlpmhLa7qneRqNIcfThjnKFMQ2I=";
        Assertions.assertEquals(originalString, aes.decrypt(encrypted));
    }
}
