/**
 * 
 */
package es.uned.lsi.pfg.service.qualification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.qualifications.QualificationsDAO;
import es.uned.lsi.pfg.model.Qualification;

/**
 * Implementacion de servicio de calificaciones
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class QualificationServiceImpl implements QualificationService {

	private static final Logger logger = LoggerFactory.getLogger(QualificationServiceImpl.class);
	
	@Autowired
	private QualificationsDAO qualificationsDAO;
	
	@Override
	public Qualification getQualification(Integer id) {
		logger.debug("getQualification: " + id);
		return qualificationsDAO.findById(id);
	}

	@Override
	public Map<Integer, Qualification> getQualificationsByStudentGroup(Integer student, Integer group) {
		logger.debug("getQualificationsByStudentGroup: " + student + "," + group);
		Map<Integer, Qualification> mapQualification = new HashMap<Integer, Qualification>();
		List<Qualification> lstQualification = qualificationsDAO.findByStudentGroup(student, group);
		if(lstQualification != null && !lstQualification.isEmpty()){
			for(Qualification qualification : lstQualification){
				mapQualification.put(qualification.getSubject(), qualification);
			}
		}
		return mapQualification;
	}

	@Override
	public List<Qualification> getQualificationsByGroupSubject(Integer group, Integer subject) {
		logger.debug("getQualificationsByStudent: " + group + ", " + subject);
		return qualificationsDAO.findByGroupSubject(group, subject);
	}

	@Transactional
	@Override
	public void upsert(List<Qualification> lstQualifications) {
		logger.debug("upsert: " + lstQualifications);
		for(Qualification qualification : lstQualifications){
			qualification.setActive(true);
			qualificationsDAO.upsert(qualification);
		}
	}
	
	@Transactional
	@Override
	public void delete(Integer qualificationId) {
		logger.debug("delete: " + qualificationId);
		Qualification qualification = qualificationsDAO.findById(qualificationId);
		qualification.setActive(false);
		qualificationsDAO.upsert(qualification);
	}

}
