package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Student;

/**
 * Repositorio de estudiantes
 * @author Carlos Navalon Urrea
 */
public interface StudentDAO {
	/**
	 * Obtiene un estudiante por su id
	 * @param id id del estudiante
	 * @return el estudiante
	 */
	public Student findStudent(String id);

	/**
	 * Obtiene todos los estudiantes
	 * @return lista todos los estudiantes
	 */
	public List<Student> findAllStudents();

	/**
	 * Inserta o actualiza un estudiante
	 * @param student estudiante
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(Student student); 

}
