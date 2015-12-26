package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService 
public interface WebServiceExample {
	@WebMethod
	public String echo(String message);
}
