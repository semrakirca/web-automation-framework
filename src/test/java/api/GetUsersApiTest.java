package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

// Stabil demo API ile GET testi
public class GetUsersApiTest {

    @Test
    public void getUsersShouldReturn200() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response =
                RestAssured
                        .given()
                        .when()
                        .get("/users");

        // Status code
        Assert.assertEquals(response.statusCode(), 200);

        // Response boş değil
        Assert.assertTrue(response.getBody().asString().length() > 0);
    }

    @Test
    public void getUsersResponseBodyTest() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response =
                RestAssured
                        .given()
                        .when()
                        .get("/users");

        // Liste boş değil mi?
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);

        // İlk user alanları var mı?
        Assert.assertNotNull(response.jsonPath().get("[0].id"));
        Assert.assertNotNull(response.jsonPath().get("[0].name"));
        Assert.assertNotNull(response.jsonPath().get("[0].email"));
    }
}
