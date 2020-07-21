/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.ws.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Nero
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(truongvl.ws.service.AccountFacadeREST.class);
        resources.add(truongvl.ws.service.AdditionalPointFacadeREST.class);
        resources.add(truongvl.ws.service.AnswerFacadeREST.class);
        resources.add(truongvl.ws.service.ChoiceOfUserFacadeREST.class);
        resources.add(truongvl.ws.service.ColorFacadeREST.class);
        resources.add(truongvl.ws.service.ColorOfProductFacadeREST.class);
        resources.add(truongvl.ws.service.HouseFacadeREST.class);
        resources.add(truongvl.ws.service.MovieCharacterFacadeREST.class);
        resources.add(truongvl.ws.service.ProductFacadeREST.class);
        resources.add(truongvl.ws.service.QuestionFacadeREST.class);
    }
    
}
