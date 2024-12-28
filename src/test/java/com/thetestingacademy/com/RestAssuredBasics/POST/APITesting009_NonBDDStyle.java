package com.thetestingacademy.com.RestAssuredBasics.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009_NonBDDStyle {



          @Test
         public void test_NonBDDStyle(){
              String payload = "{\n" +
                      "    \"username\" : \"admin\",\n" +
                      "    \"password\" : \"password123\"\n" +
                      "}";
         RequestSpecification r = RestAssured.given();
         r.baseUri("https://restful-booker.herokuapp.com");
         r.basePath("/auth");
         r.contentType(ContentType.JSON).log().all();
         r.body(payload);

         r.when().log().all().post();
         r.then().log().all().statusCode(200);



          }
}
