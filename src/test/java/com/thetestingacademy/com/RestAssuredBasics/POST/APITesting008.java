package com.thetestingacademy.com.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.client.RedirectStrategy;
import org.testng.annotations.Test;

public class APITesting008 {

      // URL: https://restful-booker.herokuapp.com/auth

    // Header : Content-Type: application/json

    // Body :
    // {
          //  "username" : "admin",
         //   "password" : "password123"
    //    }

    @Description("Verify the POST Request_BDDStyle")
    @Test
    public void test_post_BDDStyle(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)//used when there is a header
                .log().all()
                .body(payload) // As this is a post request we have to pass the body here
                .when()
                .log().all()
                .post() // As this is a post request
                .then()
                .log().all()
                .statusCode(200);

    }


}
