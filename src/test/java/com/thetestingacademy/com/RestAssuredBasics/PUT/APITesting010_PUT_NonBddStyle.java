package com.thetestingacademy.com.RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting010_PUT_NonBddStyle {



    @Description("Verify the PUT Request for the Restful Booker APIs")
    @Test
    public void test_put_nonbdd(){

        String token ="ac1e91cd290edbc";
        String bookingid = "1406";

        String payloadPUT = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        //Given Part(line no.33-39)
        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/" + bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token" , token);
        requestSpecification.body(payloadPUT).log().all();


        // When Part (line no. 42)
        Response response = requestSpecification.when().put();
        // Then Part (line no. 46-47)
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);







    }
}
