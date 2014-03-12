package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * Classe que representa um usuario do sistema de alocacao 
 *
 */
@Entity
public class User extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String name;
    private String email;
    private String password;
    
    /**
     * Construtor de classe 
     * @param name nome do usuario
     * @param email e-mail do usuario
     * @param password senha do usuario
     */
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	/**
	 * 
	 * @return o nome do usuario
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name novo nome (alteracao)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email novo email (alteracao)
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @return a senha do usuario
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password nova senha (alteracao)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return o id do usuario
	 */
	public Long getId() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}