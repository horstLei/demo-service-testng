package at.it4health.test.servicea;

import at.it4health.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class BaseDemoServiceATest extends BaseTest {

    protected Map<Object, Object> serviceAMap = null;
    private boolean initialized = false;

    protected void init(){

        if(!initialized) {
            initSession();
            serviceAMap = new HashMap<>();
            initialized = true;
        }
    }
}
