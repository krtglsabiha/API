package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01PostBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05DeleteBooking extends HerOkuAppBaseUrl {
       /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingId}}
    When
        sent delete request to the url
    Then
        status code is 201
    And
        body: Created
     */

    @Test
    public void delete(){
        spec.pathParams("first","booking"
        ,"second", bookingId);

        String expectedData = "Created";

        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        response.then()
                .statusCode(201);

        String actualData = response.asString();

        assertEquals(expectedData,actualData);











    }
}
