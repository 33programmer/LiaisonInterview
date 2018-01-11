/*
 */
package org.jboss.tools.examples.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.tools.examples.model.Developer;
import org.jboss.tools.examples.service.DeveloperRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class DeveloperController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private DeveloperRegistration developerRegistration;

    @Produces
    @Named
    private Developer newDeveloper;

    @PostConstruct
    public void initNewMember() {
        newDeveloper = new Developer();
    }

    public void register() throws Exception {
        try {
            developerRegistration.register(newDeveloper);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hired!", "Welcome to the Team!");
            facesContext.addMessage(null, m);
            initNewMember();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Sorry, but we need to think about it!");
            facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Hiring failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
