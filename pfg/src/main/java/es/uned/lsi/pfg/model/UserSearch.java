package es.uned.lsi.pfg.model;

/**
 * Bean que representa el buscador de usuarios
 * @author Carlos Navalon Urrea
 */
public class UserSearch {
	
	private Integer id;
	private String idRole;
	private String name;
	private String surname1;
	private String surname2;
	
	/**
	 * Constructor
	 */
	public UserSearch() {
		super();
	}
	
	/**
	 * Cosntructor
	 * @param id id de persona
	 * @param idRole id de perfil
	 * @param name nombre
	 * @param surname1 primer apellido
	 * @param surname2 segundo apellido
	 */
	public UserSearch(Integer id, String idRole, String name, String surname1, String surname2) {
		super();
		this.id = id;
		this.idRole = idRole;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}

	/**
	 * Obtiene el id de la persona
	 * @return el id de la persona
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Establece el id del usuario
	 * @param id el id del usuario
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Obtiene el ID del perfil
	 * @return el ID del perfil
	 */
	public String getIdRole() {
		return idRole;
	}
	/**
	 * Establece el ID del perfil
	 * @param idRole el ID del perfil
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	/**
	 * Obtiene el nombre
	 * @return el nombre
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre
	 * @param name el nombre
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtiene el primer apellido
	 * @return el primer apellido
	 */
	public String getSurname1() {
		return surname1;
	}
	/**
	 * Establece el primer apellido
	 * @param surname1 el primer apellido
	 */
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	/**
	 * Obtiene el segundo apellido
	 * @return el segundo apellido
	 */
	public String getSurname2() {
		return surname2;
	}
	/**
	 * Establece el segundo apellido
	 * @param surname2 el segundo apellido
	 */
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSearch [idUser=" + id + ", idRole=" + idRole + ", name=" + name + ", surname1=" + surname1
				+ ", surname2=" + surname2 + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname1 == null) ? 0 : surname1.hashCode());
		result = prime * result + ((surname2 == null) ? 0 : surname2.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSearch other = (UserSearch) obj;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname1 == null) {
			if (other.surname1 != null)
				return false;
		} else if (!surname1.equals(other.surname1))
			return false;
		if (surname2 == null) {
			if (other.surname2 != null)
				return false;
		} else if (!surname2.equals(other.surname2))
			return false;
		return true;
	}
}
