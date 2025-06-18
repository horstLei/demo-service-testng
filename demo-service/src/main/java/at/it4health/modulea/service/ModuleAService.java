package at.it4health.modulea.service;

import at.it4health.core.service.BaseService;

import javax.ejb.Stateless;

@Stateless
public class ModuleAService extends BaseService {

    public static final String FUNCTION_A_TESTSTRING = "ModuleAService.functionA() called";

    public String functionA(){
        return FUNCTION_A_TESTSTRING;
    }
}
