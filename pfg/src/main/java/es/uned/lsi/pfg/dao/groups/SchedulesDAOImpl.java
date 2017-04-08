/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Schedule;

/**
 * Implementacion de repositorio de horarios
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class SchedulesDAOImpl extends AbstractJpaDao implements SchedulesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulesDAOImpl.class);
	
	
	@Override
	public void saveSchedules(List<Schedule> lstSchedule) {
		logger.debug("saveSchedules: " + lstSchedule);
		try {
			for(Schedule schedule : lstSchedule){
				em.persist(schedule);
			}
		} catch (Exception e) {
			logger.error("ERROR saving schedules: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteSchudelsByGroup(Integer group) {
		logger.debug("deleteSchudelsByGroup: " + group);
		try {
			em.createNamedQuery(Schedule.Q_REMOVE_BY_GROUP)
			.setParameter("group", group).executeUpdate();
		} catch (Exception e) {
			logger.error("Error eliminando el horario para la clase " + group + ". " +  e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteTeacher(Integer teacher) {
		logger.debug("deleteTeacher: " + teacher);
		try {
			em.createNamedQuery(Schedule.Q_UPDATE_TEACHER)
			.setParameter("teacher", teacher).executeUpdate();
		} catch (Exception e) {
			logger.error("Error eliminando profesor de los horarios: " + teacher + ". " +  e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Schedule> findScheduleByGroup(Integer group) {
		logger.debug("findScheduleByGroup: " + group);
		List<Schedule> lstSchedule = new ArrayList<Schedule>();
		try {
			lstSchedule = em.createNamedQuery(Schedule.Q_FIND_BY_GROUP, Schedule.class)
				.setParameter("group", group)
				.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando horarios por clase: " + group, e);
			throw e;
		}
		return lstSchedule;
	}

	@Override
	public List<Schedule> findScheduleByTeacher(Integer teacher) {
		logger.debug("findScheduleByTeacher: " + teacher);
		List<Schedule> lstSchedule = new ArrayList<Schedule>();
		try {
			lstSchedule = em.createNamedQuery(Schedule.Q_FIND_BY_TEACHER, Schedule.class)
				.setParameter("teacher", teacher)
				.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando horarios por profesor: " + teacher, e);
			throw e;
		}
		return lstSchedule;
	}
	
	@Override
	public Integer findMaxHourByGroup(Integer group) {
		logger.debug("findMaxHourByGroup: " + group);
		try {
			return em.createNamedQuery(Schedule.Q_FIND_MAX_HOUR_BY_GROUP, Integer.class)
				.setParameter("group", group)
				.getSingleResult();
		} catch (Exception e) {
			logger.error("Error obtiendo hora máxima de horario por clase: " + group, e);
			throw e;
		}
	}

	@Override
	public Integer findMaxHourByTeacher(Integer teacher) {
		logger.debug("findMaxHourByTeacher: " + teacher);
		try {
			return em.createNamedQuery(Schedule.Q_FIND_MAX_HOUR_BY_TEACHER, Integer.class)
				.setParameter("teacher", teacher)
				.getSingleResult();
		} catch (Exception e) {
			logger.error("Error obtiendo hora máxima de horario por profesor: " + teacher, e);
			throw e;
		}
	}

}
