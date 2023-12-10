import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.http.HttpResponse;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static test_data.HerokuAppTestData.bookingDatesMapper;

public class homework extends JsonPlaceHolderBaseUrl {
     /*
            Given
            https://jsonplaceholder.typicode.com/users/1
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "name" is "Leanne Graham",
		And
		    "email" is "Sincere@april.biz"
        And
		    "city" is "Gwenborough"
		And
		    "lat" is "-37.3159"
        And
		    Company name  is "Romaguera-Crona"
     */


    @Test
    public void get(){

//        i) Set the URL
        spec.pathParams("first","users"
                ,"second",1);

//        ii)Set the expected data


//        iii) Send Request And Get Response
         Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
//        iv) Do assertions
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",is("Leanne Graham")
                        ,"email",is("Sincere@april.biz")
                        ,"city",is("Gwenborough")
                        ,"lat",is(-37.3159)
                        ,"company",is("2018-01-01")
                        ,"bookingdates.checkout", is("2019-01-01")
                        ,"additionalneeds",is("Breakfast"));
    }
}
