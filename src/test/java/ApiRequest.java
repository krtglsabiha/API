import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ApiRequest {
    /*
    In this Class we learned :
    To make API calls we need to add Rest Assured to "Pom.xml"
     how to get i)    Status Code
    ii)   Status line
    iii)  Content Type
    iv)   Header
    iv)   Response time
     */


    public static void main(String[] args) {

        Response response = given().get("https://petstore.swagger.io/v2/pet/5");

       // response.prettyPrint(); // how to print response on console

        //How To get Status Code?
        System.out.println("statusCode = " + response.statusCode());

        //How to get status Line and Content Type?
        System.out.println("statusLine = " + response.statusLine());
        System.out.println("contentType = " + response.contentType());

        //How to get value from Headers?
        System.out.println("header Date = " + response.header("Date"));
        System.out.println("header Server = " + response.header("Server"));

        //How to get All Headers?
        System.out.println();
        System.out.println("-------------Headers-----------------");
        System.out.println("headers = " + response.headers());

        //how to get response time?
        System.out.println("response time = " + response.time());


    }
}
