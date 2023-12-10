package get_requests;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Get09 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/51
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */

    @Test
    public void get(){

        // Set Url
        spec.pathParams("first","booking"
                ,"second",44);

        // Set Expected Data:
        Map<String,String> bookingMAp = bookingDatesMapper("2018-01-01","2019-01-01");
        Map<String ,Object> expectedData = herokuAppMapper("Jane","Doe",111,true,bookingMAp,"Extra pillows please");

        System.out.println(expectedData);

        // Sent Repuest and Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions:
        Map<String,Object> actualData =  response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals( bookingMAp.get("checkin"),((Map)(actualData.get("bookingdates"))) .get("checkin"));
        assertEquals( bookingMAp.get("checkout"),((Map)(actualData.get("bookingdates"))) .get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        JsonPath json = response.jsonPath();

        //  assertEquals( bookingMAp.get("checkin"),((Map)(actualData.get("bookingdates"))) .get("checkin"));
        //  assertEquals( bookingMAp.get("checkout"),((Map)(actualData.get("bookingdates"))) .get("checkout"));
        assertEquals(bookingMAp.get("checkin"), json.getString("bookingdates.checkin"));
        assertEquals(bookingMAp.get("checkout"), json.getString("bookingdates.checkout"));



    }
}
