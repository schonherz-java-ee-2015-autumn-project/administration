package hu.schonherz.administration.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;


@ManagedBean(name = "welcome")
@SessionScoped
public class Welcome implements Serializable {

	
	private static final long serialVersionUID = 4552990854623398173L;

	Logger logger = Logger.getLogger(Welcome.class);

	private String message = "Hello World!";

	@PostConstruct
	public void init() {
		try {
			logger.debug("HelloWorldBean init!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	} 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
