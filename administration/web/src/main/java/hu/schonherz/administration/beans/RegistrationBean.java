package hu.schonherz.administration.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.userdetails.UserDetailsService;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;


@RequestScoped
@ManagedBean
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class RegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	

	@EJB
	UserService userService;

	String name;
	String username;
	String phone;
	String password;
	String passconf;
	FacesMessage msg;
	FacesContext current;
	
	public void registration() {
		System.err.println("fut");
		current = FacesContext.getCurrentInstance();
		UserDTO user = new UserDTO();

		if (!isValid()) {
			System.err.println("nem valid");
			//msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A két jelszónak meg kell egyeznie!", "A két jelszónak meg kell egyeznie!");
			//current.addMessage(null, msg);
		} else {
			System.err.println("valid");
			user.setName(name);
			user.setUsername(username);
			user.setPhoneNumber(phone);
			user.setPassword(password);
			try {
				userService.registrationUser(user);
			} catch (Exception e) {
				
				System.err.println(e);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba a regisztráció közben!", "Hiba a regisztráció közben.");
				current.addMessage(null, msg);
				e.printStackTrace();
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres regisztráció!", "Sikeres regisztráció!");
			current.addMessage(null, msg);

		}
	}

	public boolean isValid(){
		return isNameValid() &&
				isUserNameValid() &&
				isPhoneValid() &&
				isPassValid() &&  
			    arePasswordsEqual();
		
	}
	
	private boolean isNameValid() {
		if(name.length()>=3 && name.length()<=150 && name.matches("[' 'a-zA-Z/-]*"))
			return true;
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Érvénytelen név", "Érvénytelen név");
			current.addMessage(null, msg);
			return false;
		}
	}
	
	private boolean isUserNameValid() {
		if( username.length()>=6 && 
			   username.length()<=16 && 
			   username.matches("[0-9a-zA-Z/_]*"))
			return true;
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Érvénytelen felhasználónév", "Érvénytelen felhasználónév");
			current.addMessage(null, msg);
			return false;
		}
	}

	private boolean isPhoneValid() {
		if( phone.length()>=7 &&
			   phone.length()<=11 &&
			   phone.matches("[0-9]+"))
			return true;
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Érvénytelen telefonszám", "Érvénytelen telefonszám");
			current.addMessage(null, msg);
			return false;
		}
		
	}

	private boolean isPassValid() {
		if( password.length()>=8 &&
			   password.length()<=12 &&
			   password.matches(".*[0-9]+.*") &&
			   password.matches(".*[a-z]+.*") &&
			   password.matches(".*[A-Z]+.*"))
			return true;
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Érvénytelen jelszó", "Érvénytelen jelszó");
			current.addMessage(null, msg);
			return false;
		}
	}
	
	private boolean arePasswordsEqual() {
		if (password.equals(passconf))
			return true;
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A két jelszónak meg kell egyeznie!", "A két jelszónak meg kell egyeznie!");
			current.addMessage(null, msg);
			return false;
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassconf() {
		return passconf;
	}

	public void setPassconf(String passconf) {
		this.passconf = passconf;
	}

}
