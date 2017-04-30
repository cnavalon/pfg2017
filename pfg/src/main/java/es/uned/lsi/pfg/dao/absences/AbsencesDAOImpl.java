/**
 * 
 */
package es.uned.lsi.pfg.dao.absences;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Absence;

/**
 * Implementacion de repositorio de faltas de asistencia
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class AbsencesDAOImpl extends AbstractJpaDao implements AbsencesDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbsencesDAOImpl.class);
	
	@Override
	public Absence findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Absence.Q_FIND_BY_ID, Absence.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando falta de asistencia: " + id, e);
			throw e;
		}
	}

	@Override
	public List<Absence> findByStudent(Integer student) {
		logger.debug("findByStudent: " + student);
		List<Absence> lstAbsences = new ArrayList<Absence>();
		try {
			lstAbsences =  em.createNamedQuery(Absence.Q_FIND_BY_STUDENT, Absence.class)
			.setParameter("student", student)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando faltas de asistencia por alumno: " + student, e);
			throw e;
		}
		return lstAbsences;
	}

	@Override
	public List<Absence> findByScheduleDay(Integer schedule, Date day) {
		logger.debug("findBySchedule: " + schedule + ", " + day);
		List<Absence> lstAbsences = new ArrayList<Absence>();
		try {
			lstAbsences =  em.createNamedQuery(Absence.Q_FIND_BY_SCHEDULE_DAY, Absence.class)
			.setParameter("schedule", schedule)
			.setParameter("day", day)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando faltas de asistencia por horario: " + schedule, e);
			throw e;
		}
		return lstAbsences;
	}

	@Override
	public Absence upsert(Absence absence) {
		logger.debug("upsert: " + absence);
		try {
			return em.merge(absence);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando falta de asistencia: " + absence, e);
			throw e;
		}
	}
	
	@Override
	public Absence findByStudentScheduleDay(Integer student, Integer schedule, Date day){
		logger.debug("findByStudentScheduleDay: " + student + ", " + schedule + ", " + day);
		try {
			return em.createNamedQuery(Absence.Q_FIND_BY_STUDENT_SCHEDULE_DAY, Absence.class)
			.setParameter("student", student)
			.setParameter("schedule", schedule)
			.setParameter("day", day)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando falta de asistencia: " + student + ", " + schedule + ", " + day, e);
			throw e;
		}
	}

}
