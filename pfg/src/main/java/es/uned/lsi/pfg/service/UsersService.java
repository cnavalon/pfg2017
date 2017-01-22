/**
 * 
 */
package es.uned.lsi.pfg.service;

import org.springframework.stereotype.Service;

/**
 * @author Carlos Navalon Urrea
 *
 */
@Service
public interface UsersService {
	public String getFullName(String id);
}
