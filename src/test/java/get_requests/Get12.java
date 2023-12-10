package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get12 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        And
            The female users are less than or equals to male users
    */

    @Test
    public void get(){
        //set url
        spec.pathParam("first","users");

        //set expected data
         //Send Request And Get Response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

       JsonPath json = response.jsonPath();

//      The value of "pagination limit" is 10
       int limit = json.getInt("meta.pagination.limit");
       assertEquals(10,limit);

//     The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String  expectedCurrentLink = "https://gorest.co.in/public/v1/users?page=1";
        String  actualCurrentLink = json.getString("meta.pagination.links.current");
        assertEquals(expectedCurrentLink,actualCurrentLink);

//            The number of users should  be 10
        int usersNum = 10;
       int numOfUsers = json.getList("data.name").size();
       assertEquals(usersNum,numOfUsers);

//            We have at least one "active" status
        response.then().body("data.status",hasItem("active")); //1.way

       Boolean isActiveExist = json.getList("data.status").contains("active"); //2.way
       assertTrue(isActiveExist);

//            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        response.then().body("data.name",hasItems("Sharda Dhawan","Ashlesh Guneta Ret.", "Bishnu Dubashi")); //1.way

      List<String> nameList = json.getList("data.name");  //2.way
      assertTrue(nameList.contains("Sharda Dhawan"));
      assertTrue(nameList.contains("Ashlesh Guneta Ret."));
      assertTrue(nameList.contains("Bishnu Dubashi"));

//            The female users are less than or equals to male users
        int numOfFemaleUsers = json.getList("data.findAll{it.gender == 'female'}").size();
        System.out.println("numOfFemaleUsers = " + numOfFemaleUsers);

        assertTrue(numOfFemaleUsers<= (numOfUsers-numOfFemaleUsers));
    }
}
