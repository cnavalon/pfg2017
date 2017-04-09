/**
 * 
 */
package es.uned.lsi.pfg.service.groups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.uned.lsi.pfg.dao.groups.CoursesDAO;
import es.uned.lsi.pfg.dao.groups.GroupsDAO;
import es.uned.lsi.pfg.dao.groups.SchedulesDAO;
import es.uned.lsi.pfg.dao.groups.SubjectDAO;
import es.uned.lsi.pfg.model.Course;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.Schedule;
import es.uned.lsi.pfg.model.ScheduleCell;
import es.uned.lsi.pfg.model.ScheduleRow;
import es.uned.lsi.pfg.model.ScheduleTable;
import es.uned.lsi.pfg.model.Subject;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Implementacion del servicio de clases
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class GroupsServiceImpl implements GroupsService {

	private static final Logger logger = LoggerFactory.getLogger(GroupsServiceImpl.class);
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	@Autowired
	private GroupsDAO groupsDAO;
	
	@Autowired 
	private SchedulesDAO schedulesDAO;
	
	@Autowired
	private SubjectDAO subjectDAO;
	
	@Override
	public List<Course> getAllCourses() {
		logger.debug("getAllCourses");
		return coursesDAO.findAll();
	}

	@Override
	public Course getCourse(Integer idCourse) {
		logger.debug("getCourse: " + idCourse);
		return coursesDAO.findById(idCourse);
	}

	@Override
	public List<Group> getAllGroups() {
		logger.debug("getAllGroups");
		return groupsDAO.findAll();
	}

	@Override
	public Group getGroup(Integer group) {
		logger.debug("getGroup: " + group);
		return groupsDAO.findById(group);
	}
	
	@Override
	public Group getGroupByCourseAndLetter(Integer course, String letter) {
		logger.debug("getGroupByCourseAndLetter: " + course + ", " + letter);
		if(letter == null || letter.equals(""))
			letter = Constans.NO_LETTER;
		return groupsDAO.findByCourseAndLetter(course,letter);
	}

	@Override
	public List<Group> getGroupsByCourse(Integer course) {
		logger.debug("getGroupsByCourse: " + course);
		return groupsDAO.findByCourse(course);
	}

	@Override
	public Integer saveGroup(Group group) {
		logger.debug("saveGroup: " + group);
		Group newGroup = groupsDAO.upsert(group);
		return newGroup.getId();
	}

	@Override
	public void deleteGroup(Integer idGroup) {
		logger.debug("deleteGroup: " + idGroup);
		Group group = groupsDAO.findById(idGroup);
		schedulesDAO.deleteSchudelsByGroup(idGroup);
		groupsDAO.remove(group);
		
	}

	@Override
	public void saveSchedule(Integer idGroup, MultipartFile file) throws IOException {
		logger.debug("saveSchedule: " + idGroup + ", " + file);
		schedulesDAO.deleteSchudelsByGroup(idGroup);
		List<Schedule> lstSchedule = processFile(idGroup, file);
		schedulesDAO.saveSchedules(lstSchedule);
	}

	/**
	 * Procesa el fichero de horarios
	 * @param idGroup
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private List<Schedule> processFile(Integer idGroup, MultipartFile file) throws IOException {
		List<Schedule> lstSchedule = new ArrayList<Schedule>();
		if(!file.isEmpty()){
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			
			Iterator<Row> itRow = sheet.iterator();
			itRow.next(); //Cabecera
			int rowIndex = 0;
		
			while (itRow.hasNext()) {
				Row row = itRow.next();
				
				//Primera columna: horas
				String[] cellHour = row.getCell(0).getStringCellValue().split(",");
				
				//Iteramos por los dias (L-V)
				for(int i = 0; i < Constans.DAYS_OF_WEEK; i++){
					String[] cellClass = row.getCell(i+1).getStringCellValue().split(",");
					Schedule schedule = new Schedule(null, idGroup, i, rowIndex, cellClass[0], Integer.parseInt(cellClass[1]), cellHour[0], cellHour[1]);
					lstSchedule.add(schedule);
				}
				rowIndex++;
			}
			xssfWorkbook.close();
		}else {
			logger.warn("Empty file: " + file);
		}
		return lstSchedule;
	}

	@Override
	public ScheduleTable getScheduleByGroup(Group group, List<Teacher> lstTeacher) {
		logger.debug("getScheduleByGroup: " + group + ", " + lstTeacher);
		Integer maxHour = schedulesDAO.findMaxHourByGroup(group.getId());
		if(maxHour != null){
			maxHour++;
			List<Schedule> lstScheduleItems = schedulesDAO.findScheduleByGroup(group.getId());
			List<Group> lstGroup = new ArrayList<Group>();
			lstGroup.add(group);
			return generateSchedule(maxHour, lstScheduleItems, lstGroup, lstTeacher);
		}
		return null;
	}

	@Override
	public ScheduleTable getScheduleByTeacher(Teacher teacher) {
		logger.debug("getScheduleByTeacher: " + teacher);
		Integer maxHour = schedulesDAO.findMaxHourByTeacher(teacher.getId());
		if(maxHour != null){
			maxHour++;
			List<Schedule> lstScheduleItems = schedulesDAO.findScheduleByTeacher(teacher.getId());
			List<Group> lstGroup = groupsDAO.findAll();
			List<Teacher> lstTeacher = new ArrayList<Teacher>();
			lstTeacher.add(teacher);
			return generateSchedule(maxHour, lstScheduleItems, lstGroup, lstTeacher);
		}
		return null;
	}

	/**
	 * Genera horario
	 * @param maxHour numero maximo de horas por dia
	 * @param lstScheduleItems listado de elementos del horario
	 * @param lstTeacher listado de profesores
	 * @param lstGroup listado de clases
	 * @param mapValues mapa de nombres 
	 * @return horario
	 */
	private ScheduleTable generateSchedule(Integer maxHour, List<Schedule> lstScheduleItems, List<Group> lstGroup, List<Teacher> lstTeacher) {
		ScheduleTable table = new ScheduleTable(maxHour, Constans.DAYS_OF_WEEK);
		Map<String, Subject> mapSubject = getMapSubject();
		Map<Integer, Group> mapGroups = getMapGroups(lstGroup);
		Map<Integer, Teacher> mapTeachers = getMapTeachers(lstTeacher);
		
		for(Schedule item : lstScheduleItems){
			ScheduleRow row = table.getRows()[item.getHour()];
			if(row.getInitHour() == null || row.getEndHour() == null){
				row.setInitHour(item.getInitHour());
				row.setEndHour(item.getEndHour());
			}
			row.getCells()[item.getDay()] = new ScheduleCell(
					mapSubject.get(item.getSubject()),
					mapTeachers.get(item.getTeacher()),
					mapGroups.get(item.getGroup()));
		}
		return table;
	}

	private Map<String, Subject> getMapSubject() {
		Map<String, Subject> mapSubject = new HashMap<String, Subject>();
		List<Subject> lstSubject = subjectDAO.findAll();
		for(Subject subject : lstSubject){
			mapSubject.put(subject.getCode(), subject);
		}
		return mapSubject;
	}
	
	private Map<Integer, Group> getMapGroups(List<Group> lstGroups) {
		Map<Integer, Group> mapGroups = new HashMap<Integer, Group>();
		for(Group group : lstGroups){
			mapGroups.put(group.getId(), group);
		}
		return mapGroups;
	}
	
	private Map<Integer, Teacher> getMapTeachers(List<Teacher> lstTeachers) {
		Map<Integer, Teacher> mapTeacher = new HashMap<Integer, Teacher>();
		for(Teacher teacher : lstTeachers){
			mapTeacher.put(teacher.getId(), teacher);
		}
		return mapTeacher;
	}

	@Override
	public Group getGroupByTutor(Integer tutor) {
		logger.debug("getGroupByTutor: " + tutor);
		return groupsDAO.findByTutor(tutor);
	}

	@Override
	public List<Subject> getSubjects() {
		logger.debug("getSubjects");
		return subjectDAO.findAll();
	}

	@Override
	public Subject getSubject(Integer idSubject) {
		logger.debug("getSubject: " + idSubject);
		return subjectDAO.findById(idSubject);
	}

	@Override
	@Transactional
	public void upsertSubject(Subject subject) {
		logger.debug("upsertSubject: " + subject);
		subjectDAO.upsert(subject);
	}

	@Override
	@Transactional
	public String deleteSubject(Integer idSubject) {
		logger.debug("deleteSubject: " + idSubject);
		Subject subject = getSubject(idSubject);
		if(schedulesDAO.findScheduleBySubject(subject.getCode()).isEmpty()){
			subjectDAO.delete(subject);
			return "subject.deleted";
		} else {
			return "subject.delete.error.existSchedule";
		}
		
	}

	@Override
	public Subject getSubjectByCode(String code) {
		logger.debug("getSubjectByCode: " + code);
		return subjectDAO.findByCode(code);
	}
	

}
