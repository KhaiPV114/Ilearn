package com.onlinelearning.Tests;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.Impl.DBContextImpl;
import com.onlinelearning.Utils.DotEnv;
import com.onlinelearning.Utils.Encryption;
import com.onlinelearning.Utils.Hashing;
import com.onlinelearning.Utils.Impl.AESEncryptionImpl;
import com.onlinelearning.Utils.Impl.HmacSHA512HashingImpl;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
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
        String originalString = "iLearn";
        Encryption aes = new AESEncryptionImpl();
        String encrypted = "5b9km23NYfbSoRdkLNE3/A==";
        Assertions.assertEquals(originalString, aes.decrypt(encrypted));
    }

}
