package com.thetestingacademy.com.PayloadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class APITesting_024_Payload_Map {

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


        // JSON -> Hashmap
    // use the Linked Hash Map
        Map<String,Object> jsonbodyUsingMap = new LinkedHashMap<>();
        jsonbodyUsingMap.put("firstname","Jim");
        jsonbodyUsingMap.put("lastname","Brown");
        jsonbodyUsingMap.put("totalprice",111);
        jsonbodyUsingMap.put("depositpaid",true);

    //Create another map for the booking dates
     Map<String,Object> bookingDatesMap = new LinkedHashMap<>();
     bookingDatesMap.put("checkin","2018-01-01");
     bookingDatesMap.put("checkout","2019-01-01");

     jsonbodyUsingMap.put("bookingdates",bookingDatesMap);
     jsonbodyUsingMap.put("additionalneeds","Breakfast");

     System.out.println(jsonbodyUsingMap);
 // The output looks like this
 // {
        // firstname=Jim,
        // lastname=Brown,
        // totalprice=111,
        // depositpaid=true,
        // bookingdates=
        // {
        //      checkin=2018-01-01,
        //       checkout=2019-01-01
        //  },
        // additionalneeds=Breakfast}

  requestSpecification = RestAssured.given();
  requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
  requestSpecification.basePath("booking");
  requestSpecification.contentType(ContentType.JSON);
  requestSpecification.body(jsonbodyUsingMap).log().all();

        Response response = requestSpecification.when().post();
        Integer bookingId = response.then().extract().path("bookingid");
   // Get Validatable Response to perform validation

   validatableResponse = response.then().log().all();
   validatableResponse.statusCode(200);

   System.out.println("Your Booking ID is --> " + bookingId);


    }
}
