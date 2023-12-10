package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
     Given
         1) https://jsonplaceholder.typicode.com/todos/198
         2) {
              "title": "Read the books"
            }
     When
          I send PATCH Request to the Url
     Then
           Status code is 200
           And response body is like   {
                                         "userId": 10,
                                         "title": "Read the books",
                                         "completed": true,
                                         "id": 198
                                        }
  */

    @Test
    public void patch(){
        //set URL
        spec.pathParams("first","todos"
        ,"second",198);

        //set expected data
        Map<String,Object> payload = jsonPlaceHolderMapper(null,"Read the books",null);
        Map<String,Object> expectedData = jsonPlaceHolderMapper(10,"Read the books",true);
        System.out.println(payload);

        //Send response and get request
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();
        payload.put("userId", 10);
        payload.put("completed", true);

        //do assertion
        Map<String,Object> actualData =  response.as(HashMap.class);

//        assertEquals(200,response.statusCode());
//        assertEquals(payload.get("userId"),actualData.get("userId"));
//        assertEquals(payload.get("title"),actualData.get("title"));
//        assertEquals(payload.get("completed"),actualData.get("completed"));

        assertEquals(200 , response.statusCode());
        assertEquals(expectedData.get("userId") , actualData.get("userId"));
        assertEquals(expectedData.get("title") , actualData.get("title"));
        assertEquals(expectedData.get("completed") , actualData.get("completed"));



    }
}
