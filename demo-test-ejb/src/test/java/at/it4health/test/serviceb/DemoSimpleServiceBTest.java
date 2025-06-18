package at.it4health.test.serviceb;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class DemoSimpleServiceBTest extends BaseDemoServiceBTest {

    @Test(groups = {"simpleTest"})
    @OperateOnDeployment("app_services")
    public void functionATest() {

        System.out.println("DemoSimpleServiceBTest.functionATest executed...");
        fail("fail is not called");
    }

}
