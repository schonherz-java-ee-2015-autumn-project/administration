package hu.schonherz.administration.wsservice.impl;

import hu.schonherz.administration.wsserviceapi.WebServiceExample;
import javax.jws.WebService;


@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.WebServiceExample")
public class WebServiceExampleImpl implements WebServiceExample {

	public String echo(String message) {
		System.out.println(message);
		return "WebServiceExample: " + message;
	}
}
