import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class JDBCTest {

    public static final Logger logger = LoggerFactory.getLogger(JDBCTest.class);

    @Rule
    public MySQLContainer mySQLContainer =  (MySQLContainer) new MySQLContainer()
            .withPassword("mysecretpw")
            .withInitScript("init.sql")
            .withLogConsumer(new Slf4jLogConsumer(logger));

    @Test
    public void testSimpleQuery() {

        ResultSet resultSet;
        String result1 = "";
        String result2 = "";

        try
        {
            JDBC jdbc = new JDBC(mySQLContainer.getJdbcUrl(),mySQLContainer.getUsername(),mySQLContainer.getPassword());

            resultSet = jdbc.submitQuery("SELECT * FROM testtable;");

            resultSet.next();
            result1 = resultSet.getString("NAME");

            resultSet.next();
            result2 = resultSet.getString("NAME");


        }
        catch(SQLException exception)
        {
            logger.debug(exception.getMessage());
        }

        assertEquals("Testcontainer",result1);
        assertEquals("Manfred",result2);

    }
}
