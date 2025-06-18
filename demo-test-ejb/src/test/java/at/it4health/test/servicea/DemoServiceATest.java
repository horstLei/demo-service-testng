package at.it4health.test.servicea;

import at.it4health.modulea.service.ModuleAService;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import static org.testng.Assert.*;


public class DemoServiceATest extends BaseDemoServiceATest{

    @Inject
    ModuleAService moduleAService;

    @BeforeMethod
    public void setupTest(){
        super.init();

        System.out.println("DemoServiceATest.setupTest called... " + moduleAService);

        assertNotNull(moduleAService, "service not correct injected");
    }

    @Test(priority = 1, groups = {"failOnFirst"})
    @OperateOnDeployment("app_services")
    public void functionATest(){

        System.out.println("DemoServiceATest.functionATest executed: " + moduleAService.functionA());

        assertEquals(ModuleAService.FUNCTION_A_TESTSTRING, moduleAService.functionA());

        // if enabled fails
        fail("fails on first TestCase");
    }

    @Test(priority = 2, groups = {"failOnSecond"})
    @OperateOnDeployment("app_services")
    public void functionBTest(){

        System.out.println("DemoServiceATest.functionBTest executed: " + moduleAService.functionA());

        assertEquals(ModuleAService.FUNCTION_A_TESTSTRING, moduleAService.functionA());
    }

    @Test(priority = 3, groups = {"arquillian","assertOnFirst"})
    @OperateOnDeployment("app_services")
    public void functionCTest(){

        System.out.println("DemoServiceATest.functionCTest executed: " + moduleAService.functionA());

        assertEquals(ModuleAService.FUNCTION_A_TESTSTRING, moduleAService.functionA());
    }

    @Test(priority = 4, groups = {"assertOnSecond"})
    @OperateOnDeployment("app_services")
    public void functionDTest(){

        System.out.println("DemoServiceATest.functionDTest executed: " + moduleAService.functionA());

        assertEquals(ModuleAService.FUNCTION_A_TESTSTRING, moduleAService.functionA() + "_test");
    }
}
