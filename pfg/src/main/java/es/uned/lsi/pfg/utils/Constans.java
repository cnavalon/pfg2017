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
	public static final String SESSION_ID = "sessionId";
	public static final String SESSION_USER = "sessionUser";
	
	//GROUPS PARAMETERS
	public static final String NO_LETTER = "-";
	public static final Integer DAYS_OF_WEEK = 5;
	
	//MEETINGS 
	public static final String MEETING_TYPE_PRIVATE = "T";
	public static final String MEETING_TYPE_PARENT = "P";
	public static final String MEETING_STATUS_PENDING = "P";
	public static final String MEETING_STATUS_ACCEPTED = "A";
	public static final String MEETING_STATUS_REJECTED = "R";
	public static final String MEETING_STATUS_CANCELED = "C";
	
	//NEWS
	public static final int NEWS_ITEMS_PAGE = 5;
	
	
	
}
