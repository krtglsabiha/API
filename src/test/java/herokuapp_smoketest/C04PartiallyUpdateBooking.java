package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import test_data.HerokuAppTestData;

import java.util.Map;

import static herokuapp_smoketest.C01PostBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04PartiallyUpdateBooking extends HerOkuAppBaseUrl {

        /*

    Given
        https://restful-booker.herokuapp.com/booking/{{bookingId}}
    And
        body: {
                "firstname" : "James",
                "lastname" : "Brown"
                }
    When
        User sends patch request
    Then
        Statuscode is 200
    And
        body: {
                "bookingid": 2146,
                "booking": {
                    "firstname": "James",
                    "lastname": "Brown",
                    "totalprice": 113,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Lunch"
                }
            }

     */

    @Test
    public void patch(){
        spec.pathParams("first","booking"
                ,"second",bookingId);

        Map<String,Object> payload = HerokuAppTestData.herokuAppMapper("James","Brown",null,null,null,null);
        System.out.println(payload);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData = new HerokuPojo("James","Brown",113,false,bookingDates,"Lunch");
        System.out.println("expectedData = " +expectedData);

//        Map<String,String> bookingDates = HerokuAppTestData.bookingDatesMapper("2018-01-01","019-01-01");
//        Map<String,Object> expectedData =  HerokuAppTestData.herokuAppMapper("James","Brown"
//                                                                             ,113,false,bookingDates
//                                                                             , "Lunch");
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200);

       HerokuPojo actualData = convertJsonToJava(response.asString(),HerokuPojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}