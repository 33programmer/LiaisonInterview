package org.jboss.tools.examples.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Developer.class)
public abstract class Developer_ {

	public static volatile SingularAttribute<Developer, String> phoneNumber;
	public static volatile SingularAttribute<Developer, String> languages;
	public static volatile SingularAttribute<Developer, String> manager;
	public static volatile SingularAttribute<Developer, String> name;
	public static volatile SingularAttribute<Developer, Long> id;
	public static volatile SingularAttribute<Developer, String> email;

}

