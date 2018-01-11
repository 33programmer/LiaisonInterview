/*
 */
package org.jboss.tools.examples.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import org.jboss.tools.examples.model.Developer;

@RequestScoped
public class DeveloperListProducer {

    @Inject
    private DeveloperRepository developerRepository;

    private List<Developer> developers;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Developer> getDevelopers() {
        return developers;
    }

    public void onDeveloperListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Developer developer) {
        retrieveAllDevelopersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllDevelopersOrderedByName() {
        developers = developerRepository.findAllOrderedByName();
    }
}
