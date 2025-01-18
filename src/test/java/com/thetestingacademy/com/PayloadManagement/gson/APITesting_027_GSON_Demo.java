package com.thetestingacademy.com.PayloadManagement.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class APITesting_027_GSON_Demo {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;


    @Test
    public void testPOSTReq(){
        //{

        //            "firstname" : "Jim",
        //                "lastname" : "Brown",
        //                "totalprice" : 111,
        //                "depositpaid" : true,
        //                "bookingdates" : {
        //            "checkin" : "2018-01-01",
        //                    "checkout" : "2019-01-01"
        //        },
        //            "additionalneeds" : "Breakfast"
        //        }


        // POJO --> JSON

        Booking booking = new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2024-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

     // In order to prepare the payload(Object is converted to JSON String)
     // Send the request

        Gson gson = new Gson();
        //Object --> JSON String(GSON) -- Serialization

        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);

  requestSpecification = RestAssured.given();
  requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
  requestSpecification.basePath("booking");
  requestSpecification.contentType(ContentType.JSON);
  requestSpecification.body(jsonStringBooking).log().all();

  Response response = requestSpecification.when().post();
  String jsonResponseString = response.asString();
  //Integer bookingId = response.then().extract().path("bookingid");

   // Get Validatable Response to perform validation

   validatableResponse = response.then().log().all();
   validatableResponse.statusCode(200);

//   System.out.println("Your Booking ID is --> " + bookingId);

 // Case1 - extract(),jsonPath().getString() --- When Response is small.

 // Case 2 - Response --> Complex JSON - Huge JSON
  // String -- Object - De Serialization


 BookingRespons bookingRespons = gson.fromJson(jsonResponseString, BookingRespons.class);
 assertThat(bookingRespons.getBookingid()).isNotZero().isNotNull();
 assertThat(bookingRespons.getBooking().getFirstname()).isEqualTo("Jim").isNotNull().isNotEmpty();



    }
 }
