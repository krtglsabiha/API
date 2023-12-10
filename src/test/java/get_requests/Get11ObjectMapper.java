package get_requests;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;
public class Get11ObjectMapper extends JsonPlaceHolderBaseUrl {
    /*
   Given
       https://jsonplaceholder.typicode.com/todos/198
   When
        I send GET Request to the URL
    Then
        Status code is 200
        And response body is like {
                                   "userId": 10,
                                   "id": 198,
                                   "title": "quis eius est sint explicabo",
                                   "completed": true
                                 }
                                 {"userId": 10,"id": 198,"title": "quis eius est sint explicabo","completed": true}
 */
    @Test
    public void get(){
        // Set Url:
        spec.pathParams("first","todos"
                ,"second",198);

        // Set Expected
        Map<String,Object> expectedData1 = jsonPlaceHolderMapper(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedData2 =  new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        // String str = "{\"userId\": 10, \"id\": 198, \"title\": \"quis eius est sint explicabo\",\"completed\": true}";
      /*  String expectedStr = "{\n" +
                "      \"userId\": 10,\n" +
                "      \"id\": 198,\n" +
                "      \"title\": \"quis eius est sint explicabo\",\n" +
                "      \"completed\": true\n" +
                "    }";
       */

        String expectedStr = JsonPlaceHolderTestData.stringBody(10,198,"quis eius est sint explicabo",true);
        Map<String,Object> expectedDataMap = convertJsonToJava(expectedStr , HashMap.class );
        JsonPlaceHolderPojo expectedDataPojo =  convertJsonToJava(expectedStr , JsonPlaceHolderPojo.class);

        System.out.println(expectedData1);
        System.out.println(expectedData2);
        System.out.println(expectedDataMap);
        System.out.println(expectedDataPojo);

        // We Set Expected Data in various ways to practice.... We can select the method which is easy to use.
        // In market ObjectMapper with Pojo is the most preferred method.

        // Sent Request And Get Response:
        Response response = given(spec).when().get("{first}/{second}");

        // Do Assertions:
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedDataPojo.getUserId(),actualData.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualData.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualData.getCompleted());
    }
}