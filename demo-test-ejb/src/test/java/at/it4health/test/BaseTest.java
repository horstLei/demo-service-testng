package at.it4health.test;

import org.testng.annotations.Listeners;
import org.jboss.arquillian.testng.Arquillian;

import java.util.HashMap;
import java.util.Map;

public class BaseTest extends ArquillianSuite{

    protected static Map<Object, Object> sessionMap = null;

    static boolean initialized = false;

    protected static void initSession(){

        if(!initialized){
            sessionMap = new HashMap<Object, Object>();
            initialized = true;
        }
    }
}
