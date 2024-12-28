package com.thetestingacademy.com.RestAssuredBasics.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting011_PATCH_NonBddStyle {

    @Description("Verify the PATCH request for the Restful Booker APIs")
    @Test

    public void test_patch_nonbdd(){

        String token = "96b4701b0e12f47";

        String bookingId = "2490";

        String payloadPatch = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";


   RequestSpecification requestSpecification = RestAssured.given();

      requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
      requestSpecification.basePath("/booking/" + bookingId);
      requestSpecification.contentType(ContentType.JSON);
      requestSpecification.cookie("token" , token);
      requestSpecification.body(payloadPatch).log().all();

        Response response = requestSpecification.when().patch();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);





    }



}
