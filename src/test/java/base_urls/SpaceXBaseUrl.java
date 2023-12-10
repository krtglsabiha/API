package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class SpaceXBaseUrl {
    protected RequestSpecification spec;
    @Before  // "Before" makes the method to be executed befare every @Test method
    public void setUp(){
        String baseUrl = "https://api.spacexdata.com/v3";

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseUrl).build();
    }
}