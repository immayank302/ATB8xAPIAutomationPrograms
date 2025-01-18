package com.thetestingacademy.com.testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting023_TestNG_InvocationCount {
        @Test(invocationCount = 5)
          public void test01(){
            Assert.assertTrue(true);
        }
}
