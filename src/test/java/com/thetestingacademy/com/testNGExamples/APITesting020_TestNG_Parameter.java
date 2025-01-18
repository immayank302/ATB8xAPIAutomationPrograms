package com.thetestingacademy.com.testNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITesting020_TestNG_Parameter {

   @Parameters("browser")
    @Test
    void demo1(String value) {
       System.out.println("Browser is : " + value);

       // Open the browser and select dadada

       if (value.equalsIgnoreCase("chrome")){
           System.out.println("Start my testing");
       }

       if (value.equalsIgnoreCase("firefox")){
           System.out.println("Start my firefox");
       }



   }


}
