package com.thetestingacademy.com.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting014_TestCaseIntegration {

    // Create Booking
    // Verify through Get  Operation

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    String bookingId;

    public String getToken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        // When - Response

        Response response = requestSpecification.when().post();

        // Then - Validatable Response
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        // Extract the token

        token = response.jsonPath().getString("token");
        System.out.println(token);

        return token;
    }

    public String getBookingID(){
          String payload_POST = "{\n" +
                  "    \"firstname\" : \"Jim\",\n" +
                  "    \"lastname\" : \"Brown\",\n" +
                  "    \"totalprice\" : 111,\n" +
                  "    \"depositpaid\" : true,\n" +
                  "    \"bookingdates\" : {\n" +
                  "       \"checkin\" : \"2018-01-01\",\n" +
                  "        \"checkout\" : \"2019-01-01\"\n" +
                  "    },\n" +
                  "    \"additionalneeds\" : \"Breakfast\"\n" +
                  "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        // When - Response

        Response response = requestSpecification.when().post();

        // Then - Validatable Response
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        // Fetch the bookingId
        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
        return bookingId;
    }

    @Test
    public void test_verify_get_request(){
          //token = getToken();
          bookingId = getBookingID();
          //System.out.println(token);
          System.out.println(getBookingID());

          requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/" + bookingId);

        response = requestSpecification.when().log().all().get();

        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        System.out.println(firstname);

        String lastname = response.jsonPath().getString("lastname");
        System.out.println(lastname);


    }

}
