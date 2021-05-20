import com.ex.controller.EmployeeController;
import com.ex.pojos.Request;
import com.ex.pojos.User;
import com.ex.service.LogInService;
import com.ex.service.RequestService;
import com.ex.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockEmployeeController {
//    private Javalin app = Javalin.create(config -> config.addStaticFiles("/html"));
//    private EmployeeController employee;
//    //.start(8080);//Start the Javalin app
//    private String employeeJson = JavalinJson.toJson(employee);
    private User current;
    RequestService request = new RequestService();
    LogInService log = new LogInService();
    UserService service = new UserService();


    @Test
    public void changePassword(){

        String password = "Mogh555";
        String confirm = "password";

        Assert.assertFalse(log.changePassword(current.getId(), password, confirm));

    }


    @Test
public void getYourRequestsTest(){
    //rootLogger.info(request.getYourRequests(currentUser.getName()));

    request.getYourRequests(current.getName());

    Assert.assertNotNull(request);
}

    @Before
    public void LoginEmployeeTest(){


        String testUsername = "Worf9";
        String testPassword = "password";

        String id = log.checkLogIn(testUsername, testPassword);
        this.current = service.findUserById(id);
        String rank = current.getRank();

        if(rank != null){
            Assert.assertNotNull(rank);
            if(rank.equals("Commander") || rank.equals("Captain")){
               Assert.assertEquals("Captain", rank);//if picard
            }
            else{
                Assert.assertEquals("Lieutenant", rank);//if picard
            }
        }
        else{
            Assert.assertNull(rank);
        }
    }

    /**
     * Submit a request to the MongoDB
     */
    @Test
    public void submitRequestTest(){
        RequestService request = new RequestService();
        Random random = new Random();
        int unique = random.nextInt(999);
        unique = 000;
        String body = "body";
        String amount = "amount";
        Date date = new Date();
        body += " [Unique ID: " + unique + "]";
        request.insertRequest(current.getName(), body, amount, date, "pending" );
        //ctx.render("/html/EmployeeRequest.html");
    }

    @After
    public void getAllRequestsTest(){
        //rootLogger.info(request.getYourRequests(currentUser.getName()));
       List<Request> list = request.getAllRequests();
       Assert.assertNotNull(list);
    }

    @After
    public void setApprovalTest(){
        String body = "body [Unique ID: 000]";
        //rootLogger.info(body);
        request.validateRequest(body);
        //ctx.render("/html/ManagerRequests.html");
    }

}
