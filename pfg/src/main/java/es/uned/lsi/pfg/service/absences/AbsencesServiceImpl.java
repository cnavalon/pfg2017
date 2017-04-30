/**
 * 
 */
package es.uned.lsi.pfg.service.absences;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.absences.AbsencesDAO;
import es.uned.lsi.pfg.model.Absence;

/**
 * Implementacion de servicio de faltas de asistenca
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class AbsencesServiceImpl implements AbsencesService {

	private static final Logger logger = LoggerFactory.getLogger(AbsencesServiceImpl.class);

	@Autowired
	private AbsencesDAO absencesDAO;
	
	@Transactional
	@Override
	public List<Absence> upsert(List<Absence> lstAbsences) {
		logger.debug("upsert: " + lstAbsences);
		List<Absence> lstSaved = new ArrayList<Absence>();
		for(Absence absence : lstAbsences){
			Absence oldAbsence = absencesDAO.findByStudentScheduleDay(
					absence.getStudent().getId(),
					absence.getSchedule().getId(),
					absence.getDay());
			if(oldAbsence == null){
				if(!absence.isActive())
					continue;
			} else {
				absence.setId(oldAbsence.getId());
			}
			lstSaved.add(absencesDAO.upsert(absence));
		}	
		return lstSaved;
	}

	@Override
	public List<Integer> getStudentsAbsencesByScheduleDay(Integer schedule, Date day) {
		logger.debug("getMapAbsencesByScheduleDay: " + schedule + ", " + day);
		List<Integer> lstStudents = new ArrayList<Integer>();
		List<Absence> lstAbsences = absencesDAO.findByScheduleDay(schedule, day);
		for(Absence absence : lstAbsences){
			lstStudents.add(absence.getStudent().getId());
		}
		return lstStudents;
	}

	@Override
	public List<Absence> getAbsencesByStudent(Integer studentId) {
		logger.debug("getAbsencesByStudent: " + studentId);
		return absencesDAO.findByStudent(studentId);
	}
	
}
