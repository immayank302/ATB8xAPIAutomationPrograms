package com.thetestingacademy.com.testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;
// Unless the method serverStartedOk will not execute(or get failed) the other 2 methods method1 and method2
// will not execute
public class APITesting019_TestNG_DependsOnMethod {

    @Test
    public void serverStartedOk(){
        System.out.println("I will run first");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method1(){
        System.out.println("method1");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method2(){
        System.out.println("method2");
        Assert.assertTrue(true);
    }
}
