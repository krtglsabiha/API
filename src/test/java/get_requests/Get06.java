package get_requests;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/11
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
                    {
                        "firstname": "John",
                        "lastname": "Smith",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "Breakfast"
                    }
 */
    @Test
    public void get(){
        //i) Set the Url
        spec.pathParams("first","booking"
                ,"second",41);
        //ii) Set the Expected Data
        //iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}/{second}");   //  "{}/{}/{}"
        response.prettyPrint();
        //iv)  Do Assertions
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",is("John")
                        ,"lastname",is("Smith")
                        ,"totalprice",is(111)
                        ,"depositpaid",is(true)
                        ,"bookingdates.checkin",is("2018-01-01")
                        ,"bookingdates.checkout", is("2019-01-01")
                        ,"additionalneeds",is("Breakfast"));

        // Second Way: --------> JsonPath ----> It helps you to work with body as you wish.
        //First you need to change response to JsonPath data format and save it as a variable
       JsonPath json = response.jsonPath();
       assertEquals("John",json.getString("firstname"));
       assertEquals("Smith",json.getString("lastname"));
       assertEquals(111,json.getInt("totalprice"));
       assertEquals(true,json.getBoolean("depositpaid"));
       assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
       assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
       assertEquals("Breakfast",json.getString("additionalneeds"));


       // do assertion with soft assertion----> Soft assertion can be done by TestNG Library

        //There are 3 steps to make TestNG softassert:
        //1. Create SoftAssert object:
        SoftAssert softAssert = new SoftAssert();

        //2. Do assertions by softAssert object
        softAssert.assertEquals(json.getString("firstname"),"John");
        softAssert.assertEquals(json.getString("lastname"),"Smith");
        softAssert.assertEquals(json.getInt("totalprice"),111);
        softAssert.assertEquals(json.getBoolean("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast");

        //3. finish All assertions by assertAll() method
        softAssert.assertAll();


    }
}






