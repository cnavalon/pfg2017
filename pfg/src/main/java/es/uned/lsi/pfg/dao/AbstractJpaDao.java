package es.uned.lsi.pfg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Carlos Navalon Urrea
 * Clase abstracta de repositorio JPA
 */
public abstract class AbstractJpaDao {
	
	@PersistenceContext
    protected EntityManager em;
}
