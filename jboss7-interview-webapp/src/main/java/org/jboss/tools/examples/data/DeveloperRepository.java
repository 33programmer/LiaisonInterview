/*
 */
package org.jboss.tools.examples.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import org.jboss.tools.examples.model.Developer;

@ApplicationScoped
public class DeveloperRepository {

    @Inject
    private EntityManager em;

    public Developer findById(Long id) {
        return em.find(Developer.class, id);
    }

    public Developer findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = cb.createQuery(Developer.class);
        Root<Developer> developer = criteria.from(Developer.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(developer.get(Developer_.name), email));
        criteria.select(developer).where(cb.equal(developer.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Developer> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = cb.createQuery(Developer.class);
        Root<Developer> developer = criteria.from(Developer.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(developer.get(Developer_.name)));
        criteria.select(developer).orderBy(cb.asc(developer.get("name")));
        return em.createQuery(criteria).getResultList();
    }
        
    // Good to have in the future
      public Developer findByManager(String manager) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = cb.createQuery(Developer.class);
        Root<Developer> developer = criteria.from(Developer.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(developer).where(cb.equal(developer.get("manager"), manager)); // QUERY WHERE manager=''
        return em.createQuery(criteria).getSingleResult();
    }
}
