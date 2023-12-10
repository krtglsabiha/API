package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get01 {
    /*
    1) We will use Postman for Manuel Tests
    2) We will use Rest Assures for API automation testing
    3) To type automations scripts follow the following steps:
        a) Understand the requirements
        b) We will use "Gherkin Language" to type test
            There are four keywords in "Gherkin Language":
            i)Given : used for pre conditions (url, body, accept type, content type, ....)
            ii)When : used for action (get, post, put, ....)
            iii)Then : used for assertions
            iv)And : used for multiple usage of Keywords
        c)Start to type Automation Script :
            i) Set the URL
            ii)Set the expected data
            iii) Send Request And Get Response
            iv) Do assertions
     */
 /*
 Given
    https://restful-booker.herokuapp.com/booking/10
 When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be application/json
And
    Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get(){

//        i) Set the URL
        String url= "https://restful-booker.herokuapp.com/booking/10";

//        ii)Set the Expected Data ------>we will do it later
//        iii) Send Request And Get Response
        Response response = given().when().get(url);
        response.prettyPrint();

//        iv) Do assertions
        response.then().   //after "then()" assertion stars...
                statusCode(200).   //HTTP Status Code should be 200
                contentType("application/json").   // Content Type should be application/json
                statusLine("HTTP/1.1 200 OK");  //Status Line should be HTTP/1.1 200 OK
//        assertEquals(200,response.statusCode());  //assertion by "Junit"
    }

    @Test
    public void get02(){
        String url= "https://restful-booker.herokuapp.com/booking/10";
        given().
                when().
                get(url).
                then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON);
    }
}
