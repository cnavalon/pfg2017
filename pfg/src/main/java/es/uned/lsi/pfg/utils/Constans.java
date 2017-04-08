package es.uned.lsi.pfg.utils;

/**
 * Constantes
 * @author Carlos Navalon Urrea
 */
public interface Constans {
	//ROLES
	public static final String ROLE_ADMIN = "ROLE_ADM";
	public static final String ROLE_TEACHER = "ROLE_TCH";
	public static final String ROLE_STUDENT = "ROLE_STD";
	public static final String ROLE_PARENT = "ROLE_PAR";
	public static final String[] ROLES_ARRAY = {ROLE_ADMIN, ROLE_TEACHER, ROLE_STUDENT, ROLE_PARENT};
	
	//SESSION FIELDS
	public static final String SESSION_USER_NAME = "sessionUserFullName";
	public static final String SESSION_ROLE = "sessionUserRole";
	public static final String SESSION_USER_ID = "sessionUserId";
	
	//GROUPS PARAMETERS
	public static final String NO_LETTER = "-";
	public static final Integer DAYS_OF_WEEK = 5;
	
	
	
}
