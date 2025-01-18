package com.thetestingacademy.com.PayloadManagement;

import com.thetestingacademy.com.PayloadManagement.gson.Booking;
import com.thetestingacademy.com.PayloadManagement.gson.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_026_Payload_POJO {

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


  requestSpecification = RestAssured.given();
  requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
  requestSpecification.basePath("booking");
  requestSpecification.contentType(ContentType.JSON);
  requestSpecification.body(booking).log().all();

  Response response = requestSpecification.when().post();
  Integer bookingId = response.then().extract().path("bookingid");

   // Get Validatable Response to perform validation

   validatableResponse = response.then().log().all();
   validatableResponse.statusCode(200);

   System.out.println("Your Booking ID is --> " + bookingId);


    }
 }
