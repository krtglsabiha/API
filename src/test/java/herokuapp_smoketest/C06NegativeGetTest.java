package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01PostBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06NegativeGetTest extends HerOkuAppBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking/{{userId}}

    When
        sent get request to the url
    Then
        status code is 404
    And
        body: Not Found
     */

    @Test
    public void get(){
        spec.pathParams("first","booking"
                ,"second",bookingId);

        String expectedData = "Not Found";
        System.out.println("expectedData = " +expectedData);

        Response response =  given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();
        assertEquals(expectedData,actualData);
    }
}

