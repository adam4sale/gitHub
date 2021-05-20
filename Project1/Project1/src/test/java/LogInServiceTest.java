import com.ex.dao.MongoDao;
import com.ex.pojos.LogIn;

import com.ex.service.LogInService;
import junit.framework.AssertionFailedError;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LogInServiceTest {
    MongoDao dao = new MongoDao();
    LogInService logIn = new LogInService();
    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

    /**
     * Method that uses the MongoDao to get a list of users.
     * The MongoDB should not be empty for the is test to pass.
     * Asserts a list of employees onboard enterprise
     */
    @Test
    public void getLogInsTest() throws AssertionFailedError {
        List<LogIn> test = dao.listLogIns();
        boolean flag = !test.isEmpty();
        rootLogger.info(flag + " is the value of the boolean flag in the LogInServiceTest");
        Assert.assertTrue(flag);//should come back positive
    }

    @Test
    public void getListTest(){
        List<LogIn> testList = logIn.getLogIns();
        rootLogger.info("list at position 0", testList);
        Assert.assertNotNull(testList);

    }


    @Test
    public void getUserLogInTest() throws AssertionFailedError{
        LogIn worf = new LogIn("Worf9", "password", null);

        List<LogIn> testList = dao.listLogIns();

        for(LogIn log: testList){
            String user = log.getUsername();
            rootLogger.info(user);
            if(user.equals(worf.getUsername())){
                Assert.assertEquals("Worf has a password ", log.getPassword(), worf.getPassword() );
                rootLogger.info("The password was found!");
                break;
            }
            else{
                rootLogger.info("The test didn't to the if statement but to the else...");
            }
        }
    }
}
