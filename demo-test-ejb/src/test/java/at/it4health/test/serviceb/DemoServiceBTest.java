package at.it4health.test.serviceb;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


public class DemoServiceBTest extends BaseDemoServiceBTest {

    @Test(groups = {"failOnFirst"})
    @OperateOnDeployment("app_services")
    public void functionATest() {

        System.out.println("DemoServiceBTest.functionATest executed...");
        fail("fail is not called");
    }

    @Test(groups = {"failOnSecond"})
    @OperateOnDeployment("app_services")
    public void functionBTest() {
        System.out.println("DemoServiceBTest.functionBTest executed...");
        fail("fail is not called");
    }

    @Test(groups = {"assertOnFirst"})
    @OperateOnDeployment("app_services")
    public void functionCTest() {
        System.out.println("DemoServiceBTest.functionCTest executed...");
        assertEquals(1, 1);
    }

    @Test(groups = {"assertOnSecond"})
    @OperateOnDeployment("app_services")
    public void functionDTest() {
        System.out.println("DemoServiceBTest.functionDTest executed...");
        assertEquals(1, -1);
    }
}
