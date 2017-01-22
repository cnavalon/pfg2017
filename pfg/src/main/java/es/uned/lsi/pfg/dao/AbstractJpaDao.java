package es.uned.lsi.pfg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao {
	
	@PersistenceContext
    protected EntityManager em;
}
