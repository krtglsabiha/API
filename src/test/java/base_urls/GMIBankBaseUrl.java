package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationGMIBank.generateToken;

public class GMIBankBaseUrl {


    protected RequestSpecification spec;

    @Before  // "Before" makes the method to be executed before every @Test method

    public void setUp(){
        String baseUrl = "https://gmibank.com/";
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+ generateToken())
                .setBaseUri(baseUrl).build();
    }

}
