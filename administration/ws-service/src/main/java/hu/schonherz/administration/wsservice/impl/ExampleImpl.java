package hu.schonherz.administration.wsservice.impl;

import hu.schonherz.administration.wsserviceapi.Example;

import javax.ejb.Stateless;
import javax.jws.WebService;


@Stateless(mappedName = "exampleimpll")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.Example",
			serviceName="exampleService",
			portName="examplePort")
public class ExampleImpl implements Example {

	
	public String echo(String message) {
		System.out.println(message);
		return "Example: " + message;
	}
}
