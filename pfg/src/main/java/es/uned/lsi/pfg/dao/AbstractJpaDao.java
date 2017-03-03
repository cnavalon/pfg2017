package es.uned.lsi.pfg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase abstracta de repositorio JPA
 * @author Carlos Navalon Urrea
 */
public abstract class AbstractJpaDao {
	
	@PersistenceContext
    protected EntityManager em;
}
