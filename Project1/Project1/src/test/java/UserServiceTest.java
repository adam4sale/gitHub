import com.ex.dao.MongoDao;
import com.ex.pojos.LogIn;
import com.ex.pojos.Request;
import com.ex.pojos.User;
import com.ex.service.UserService;
// import com.sun.xml.internal.ws.policy.AssertionSet;
import junit.framework.AssertionFailedError;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class UserServiceTest {
    MongoDao dao = new MongoDao();
    UserService user = new UserService();
    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

    @Test
    public void getListTest(){
        List<User> testList = user.getUsers();
        rootLogger.info("list at position 0", testList);
        Assert.assertNotNull(testList);

    }

    /**
     * Method that uses the MongoDao to get a list of users.
     * The MongoDB should not be empty for the is test to pass.
     *
     */
    @Test
    public void getUsersTest() throws AssertionFailedError {
        List <User> test = dao.listUsers();
        boolean flag = !test.isEmpty();
        rootLogger.info(flag + " is the value of the boolean flag in the UserServiceTest");
        Assert.assertTrue(flag);//should come back positive
    }

        @Test
    public void getAllRequestsTest()throws AssertionFailedError {

            // Map<> fakeDao = new HashMap();
            List<Request> list = dao.listRequests();
            //remove all requests where isApproved() is true
            Iterator<Request> i = list.iterator();

            while (i.hasNext()) {
                Request rq = i.next();
                if (rq.isApproved().equals("approved") || rq.isApproved().equals("declined")) {
                    // Assert.assertNotNull(rq);
                    Assert.assertEquals("There should be no 'approved' or 'declined'", rq.isApproved(), "pending");
                    //i.remove();
                }
            }
        }
    @Test
    public void findUserById(){
        String test = "Worf";
        User user = dao.findUserById("888444555");
        Assert.assertEquals("This is Worf's ID", user.getName(), test);
    }

}
