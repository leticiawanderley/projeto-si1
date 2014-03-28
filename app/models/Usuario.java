package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * Classe que representa um usuario do sistema de alocacao 
 *
 */
@Entity
public class Usuario extends Model {

	private static final long serialVersionUID = 1L;
	private String name;
	@Id
    private String email;
    private String password;
    
    public static Finder<String,Usuario> find = new Finder<String,Usuario>(String.class, Usuario.class); 
    
    /**
     * Construtor de classe 
     * @param name nome do usuario
     * @param email e-mail do usuario
     * @param password senha do usuario
     */
	public Usuario(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		// this.password = BCrypt.hashpw(password, BCrypt.gensalt()); 
		//   this.save();
		this.password = password; 
		/*this.save();*/
	}
	
    public  Usuario authenticate(String email, String password) {
    		    // metodo para checar se senha confere
    		    /*if (BCrypt.checkpw(password, this.password)) {
    		    	System.out.println("reconheceu");
    		    } else
    		    	System.out.println("nao reconheceu");*/

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
		Usuario other = (Usuario) obj;
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