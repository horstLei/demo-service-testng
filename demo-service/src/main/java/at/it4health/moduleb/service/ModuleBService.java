package at.it4health.moduleb.service;

import at.it4health.core.service.BaseService;

import javax.ejb.Stateless;

@Stateless
public class ModuleBService extends BaseService implements ModuleBApi {

    @Override
    public String functionA() {
        return "hello World";
    }

    @Override
    public String functionB(String testText) {
        return "hello " + testText;
    }
}
