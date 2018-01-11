
package org.jboss.tools.examples.service;

import org.jboss.tools.examples.model.Developer;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class DeveloperRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Developer> developerEventSrc;

    public void register(Developer developer) throws Exception {
        log.info("Wow you know?: " + developer.getLanguages());
        em.persist(developer);
        developerEventSrc.fire(developer);
    }
}
