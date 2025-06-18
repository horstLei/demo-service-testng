package at.it4health.test.servicea;

import at.it4health.modulea.service.ModuleAService;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;


public class DemoSimpleServiceATest extends BaseDemoServiceATest{

    @Inject
    ModuleAService moduleAService;

    @BeforeMethod
    public void setupTest(){    
        super.init();
    }

    @Test(groups = {"simpleTest"})
    @OperateOnDeployment("app_services")
    public void functionATest(){

        System.out.println("DemoSimpleServiceATest.functionATest executed: " + moduleAService.functionA());

        assertEquals(ModuleAService.FUNCTION_A_TESTSTRING, moduleAService.functionA());

        // if enabled fails
        //fail("fails on first TestCase");
    }
}
