package hu.schonherz.administration.wsservice.impl;

import hu.schonherz.administration.wsserviceapi.WebServiceExample;

import javax.ejb.Stateless;
import javax.jws.WebService;


@Stateless(mappedName = "WebService")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.WebServiceExample")
public class WebServiceExampleImpl implements WebServiceExample {

	public String echo(String message) {
		System.out.println(message);
		return "WebServiceExample: " + message;
	}
}
