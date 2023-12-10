package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {
    //Given
    //       https://jsonplaceholder.typicode.com/todos
    //And
    //    Accept type is “application/json”
    //When
    //  I send a GET request to the Url
    //Then
    //    HTTP Status Code should be 200
    //And
    //    Response format should be "application/json"
    //And
    //    There should be 200 todos
    //And
    //    "quis eius est sint explicabo" should be one of the todos title
    //And
    //    2, 7, and 9 should be among the userIds

    @Test
    public void get(){

//        i) Set the URL
        spec.accept(ContentType.JSON).pathParams("first","todos");
//        ii)Set the expected data
//        iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}");
//        response.prettyPrint();

//        iv) Do assertions
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
//              .body("[0].title",equalTo("delectus aut autem"));  // --> To check Json value from a list of Jsons first write its index.---> "[0].title"
                .body("title",hasSize(200)
                         ,"title",hasItem("quis eius est sint explicabo")
                         ,"userId",hasItems(2,7,9));

        /*
            When we have response in Collection, we can:
                i)  check its size by hasSize() method,
                ii) check if an element exists in the collection by hasItem() method,
                iii) check if multiple elements exist by hasItems() method,

         */
    }
}
