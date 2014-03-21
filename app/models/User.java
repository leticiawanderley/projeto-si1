package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.mindrot.jbcrypt.BCrypt;

import play.db.ebean.Model;

/**
 * Classe que representa um usuario do sistema de alocacao 
 *
 */
@Entity
public class User extends Model {

	private static final long serialVersionUID = 1L;
	private String name;
	@Id
    private String email;
    private String password;
    public static Finder<String,User> find = new Finder<String,User>(String.class, User.class); 
    
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt()); 
		this.save();
	}
	
    public static User authenticate(String email, String password) {
        return find.where().eq("email", email).eq("password", password).findUnique();
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}