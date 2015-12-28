package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService 
public interface Example {
	@WebMethod
	public String echo(String message);

}
