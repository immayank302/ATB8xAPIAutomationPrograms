package com.thetestingacademy.com.RestAssuredBasics.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting012_DELETE_NonBddStyle {

   @Description("Verify the delete request for the Restful Booker APIs")
    @Test
    public void test_delete_nonbdd(){

          String token = "5393a757f80aa13";

          String bookingId = "1293";

       RequestSpecification requestSpecification = RestAssured.given();

       requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
       requestSpecification.basePath("/booking/" + bookingId);
       requestSpecification.contentType(ContentType.JSON);
       requestSpecification.cookie("token" , token);
       requestSpecification.auth().preemptive().basic("admin","password123");
       requestSpecification.log().all();

       Response response = requestSpecification.when().delete();

       ValidatableResponse validatableResponse = response.then().log().all();
       validatableResponse.statusCode(201);

    }
}
